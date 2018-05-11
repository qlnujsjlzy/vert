package com.zhaoyang.vert.module.system.controller;

import com.zhaoyang.vert.core.base.controller.BaseController;
import com.zhaoyang.vert.core.common.annotion.BusinessLog;
import com.zhaoyang.vert.core.common.annotion.Permission;
import com.zhaoyang.vert.core.common.constant.dictmap.DeptDict;
import com.zhaoyang.vert.core.common.constant.enums.BizExceptionEnum;
import com.zhaoyang.vert.core.exception.VertException;
import com.zhaoyang.vert.core.log.PrototypeBeanInstance;
import com.zhaoyang.vert.core.node.ZTreeNode;
import com.zhaoyang.vert.core.util.ToolUtil;
import com.zhaoyang.vert.module.system.factory.DaoFactory;
import com.zhaoyang.vert.module.system.model.Dept;
import com.zhaoyang.vert.module.system.service.DeptService;
import com.zhaoyang.vert.module.system.wrapper.DeptWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 部门控制器
 *
 * @author fengshuonan
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private String PREFIX = "/system/dept/";

    @Autowired
    private DeptService deptService;

    @Resource
    private PrototypeBeanInstance prototypeBeanInstance;

    /**
     * 跳转到部门管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dept.html";
    }

    /**
     * 跳转到添加部门
     */
    @RequestMapping("/dept_add")
    public String deptAdd() {
        return PREFIX + "dept_add.html";
    }

    /**
     * 跳转到修改部门
     */
    @Permission
    @RequestMapping("/dept_update/{deptId}")
    public String deptUpdate(@PathVariable Integer deptId, Model model) {
        Dept dept = deptService.selectById(deptId);
        model.addAttribute(dept);
        model.addAttribute("pName",DaoFactory.instance().getDeptName(dept.getPId()));
        prototypeBeanInstance.getLogSessionHolder().setObject(dept);
        return PREFIX + "dept_edit.html";
    }

    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 新增部门
     */
    @BusinessLog(value = "添加部门", key = "simplename", dict = DeptDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(Dept dept) {
        if (ToolUtil.isOneEmpty(dept, dept.getSimpleName())) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        //完善pids,根据pid拿到pid的pids
        deptSetPids(dept);
        return this.deptService.insert(dept);
    }

    /**
     * 获取所有部门列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.deptService.list(condition);
        return super.wrapObject(new DeptWrapper(list));
    }

    /**
     * 部门详情
     */
    @RequestMapping(value = "/detail/{deptId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("deptId") Integer deptId) {
        return deptService.selectById(deptId);
    }

    /**
     * 修改部门
     */
    @BusinessLog(value = "修改部门", key = "simplename", dict = DeptDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public Object update(Dept dept) {
        if (ToolUtil.isEmpty(dept) || dept.getId() == null) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        deptSetPids(dept);
        deptService.updateById(dept);
        return SUCCESS_TIP;
    }

    /**
     * 删除部门
     */
    @BusinessLog(value = "删除部门", key = "deptId", dict = DeptDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public Object delete(@RequestParam Integer deptId) {

        //缓存被删除的部门名称
        prototypeBeanInstance.getLogSessionHolder().setObject(DaoFactory.instance().getDeptName(deptId));

        deptService.deleteDept(deptId);

        return SUCCESS_TIP;
    }

    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPId()) || dept.getPId().equals(0)) {
            dept.setPId(0);
            dept.setPIds("[0],");
        } else {
            int pid = dept.getPId();
            Dept temp = deptService.selectById(pid);
            String pids = temp.getPIds();
            dept.setPId(pid);
            dept.setPIds(pids + "[" + pid + "],");
        }
    }
}
