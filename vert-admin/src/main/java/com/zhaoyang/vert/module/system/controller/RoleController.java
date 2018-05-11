package com.zhaoyang.vert.module.system.controller;

import com.zhaoyang.vert.core.base.controller.BaseController;
import com.zhaoyang.vert.core.base.tips.Tip;
import com.zhaoyang.vert.core.cache.CacheKey;
import com.zhaoyang.vert.core.cache.CacheKit;
import com.zhaoyang.vert.core.common.annotion.BusinessLog;
import com.zhaoyang.vert.core.common.annotion.Permission;
import com.zhaoyang.vert.core.common.constant.Const;
import com.zhaoyang.vert.core.common.constant.dictmap.RoleDict;
import com.zhaoyang.vert.core.common.constant.enums.BizExceptionEnum;
import com.zhaoyang.vert.core.exception.VertException;
import com.zhaoyang.vert.core.log.PrototypeBeanInstance;
import com.zhaoyang.vert.core.node.ZTreeNode;
import com.zhaoyang.vert.core.util.ConvertUtil;
import com.zhaoyang.vert.core.util.ToolUtil;
import com.zhaoyang.vert.module.system.factory.DaoFactory;
import com.zhaoyang.vert.module.system.model.Role;
import com.zhaoyang.vert.module.system.model.User;
import com.zhaoyang.vert.module.system.service.RoleService;
import com.zhaoyang.vert.module.system.service.UserService;
import com.zhaoyang.vert.module.system.wrapper.RoleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static String PREFIX = "/system/role";
    @Resource
    private PrototypeBeanInstance prototypeBeanInstance;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 跳转到角色列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/role.html";
    }

    /**
     * 跳转到添加角色
     */
    @RequestMapping(value = "/role_add")
    public String roleAdd() {
        return PREFIX + "/role_add.html";
    }

    /**
     * 跳转到修改角色
     */
    @Permission
    @RequestMapping(value = "/role_edit/{roleId}")
    public String roleEdit(@PathVariable Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = this.roleService.selectById(roleId);
        model.addAttribute(role);
        model.addAttribute("pName", DaoFactory.instance().getSingleRoleName(role.getPId()));
        model.addAttribute("deptName", DaoFactory.instance().getDeptName(role.getDeptId()));
        prototypeBeanInstance.getLogSessionHolder().setObject(role);
        return PREFIX + "/role_edit.html";
    }

    /**
     * 跳转到角色分配
     */
    @Permission
    @RequestMapping(value = "/role_assign/{roleId}")
    public String roleAssign(@PathVariable("roleId") Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", DaoFactory.instance().getSingleRoleName(roleId));
        return PREFIX + "/role_assign.html";
    }

    /**
     * 获取角色列表
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String roleName) {
        List<Map<String, Object>> roles = this.roleService.selectRoles(super.getPara("roleName"));
        return super.wrapObject(new RoleWrapper(roles));
    }

    /**
     * 角色新增
     */
    @RequestMapping(value = "/add")
    @BusinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        role.setId(null);
        this.roleService.insert(role);
        return SUCCESS_TIP;
    }

    /**
     * 角色修改
     */
    @RequestMapping(value = "/edit")
    @BusinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip edit(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.updateById(role);

        //删除缓存
        CacheKit.removeAll(CacheKey.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "/remove")
    @BusinessLog(value = "删除角色", key = "roleId", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip remove(@RequestParam Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }

        //不能删除超级管理员角色
        if (roleId.equals(Const.ADMIN_ROLE_ID)) {
            throw new VertException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }

        //缓存被删除的角色名称
        prototypeBeanInstance.getLogSessionHolder().setObject(DaoFactory.instance().getSingleRoleName(roleId));

        this.roleService.delRoleById(roleId);

        //删除缓存
        CacheKit.removeAll(CacheKey.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 查看角色
     */
    @RequestMapping(value = "/view/{roleId}")
    @ResponseBody
    public Tip view(@PathVariable Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.selectById(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 配置权限
     */
    @RequestMapping("/setAuthority")
    @BusinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip setAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeList")
    @ResponseBody
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Integer userId) {
        User theUser = this.userService.selectById(userId);
        String roleid = theUser.getRoleId();
        if (ToolUtil.isEmpty(roleid)) {
            List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
            return roleTreeList;
        } else {
            String[] strArray = ConvertUtil.toStrArray(",", roleid);
            List<ZTreeNode> roleTreeListByUserId = this.roleService.roleTreeListByRoleId(strArray);
            return roleTreeListByUserId;
        }
    }

}
