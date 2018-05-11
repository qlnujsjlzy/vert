package com.zhaoyang.vert.module.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhaoyang.vert.core.base.controller.BaseController;
import com.zhaoyang.vert.core.base.tips.Tip;
import com.zhaoyang.vert.core.common.annotion.BusinessLog;
import com.zhaoyang.vert.core.common.annotion.Permission;
import com.zhaoyang.vert.core.common.constant.Const;
import com.zhaoyang.vert.core.common.constant.dictmap.MenuDict;
import com.zhaoyang.vert.core.common.constant.enums.BizExceptionEnum;
import com.zhaoyang.vert.core.common.constant.enums.MenuStatusEnum;
import com.zhaoyang.vert.core.exception.VertException;
import com.zhaoyang.vert.core.log.PrototypeBeanInstance;
import com.zhaoyang.vert.core.node.ZTreeNode;
import com.zhaoyang.vert.core.support.BeanKit;
import com.zhaoyang.vert.core.util.ToolUtil;
import com.zhaoyang.vert.module.system.factory.DaoFactory;
import com.zhaoyang.vert.module.system.model.Menu;
import com.zhaoyang.vert.module.system.service.MenuService;
import com.zhaoyang.vert.module.system.wrapper.MenuWrapper;
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
 * 菜单控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private static String PREFIX = "/system/menu/";
    @Resource
    private PrototypeBeanInstance prototypeBeanInstance;
    @Autowired
    private MenuService menuService;

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "menu.html";
    }

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping(value = "/menu_add")
    public String menuAdd() {
        return PREFIX + "menu_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/menu_edit/{menuId}")
    public String menuEdit(@PathVariable Long menuId, Model model) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        Menu menu = this.menuService.selectById(menuId);

        //获取父级菜单的id
        Menu temp = new Menu();
        temp.setCode(menu.getPCode());
        Menu pMenu = this.menuService.selectOne(new EntityWrapper<>(temp));

        //如果父级是顶级菜单
        if (pMenu == null) {
            menu.setPCode("0");
        } else {
            //设置父级菜单的code为父级菜单的id
            menu.setPCode(String.valueOf(pMenu.getId()));
        }

        Map<String, Object> menuMap = BeanKit.beanToMap(menu);
        menuMap.put("pcodeName", DaoFactory.instance().getMenuNameByCode(temp.getCode()));
        model.addAttribute("menu", menuMap);
        prototypeBeanInstance.getLogSessionHolder().setObject(menu);
        return PREFIX + "menu_edit.html";
    }

    /**
     * 修该菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/edit")
    @BusinessLog(value = "修改菜单", key = "name", dict = MenuDict.class)
    @ResponseBody
    public Tip edit(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        //设置父级菜单编号
        menuSetPcode(menu);

        this.menuService.updateById(menu);
        return SUCCESS_TIP;
    }

    /**
     * 获取菜单列表
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String menuName, @RequestParam(required = false) String level) {
        List<Map<String, Object>> menus = this.menuService.selectMenus(menuName, level);
        return super.wrapObject(new MenuWrapper(menus));
    }

    /**
     * 新增菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/add")
    @BusinessLog(value = "菜单新增", key = "name", dict = MenuDict.class)
    @ResponseBody
    public Tip add(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }

        //判断是否存在该编号
        String existedMenuName = DaoFactory.instance().getMenuNameByCode(menu.getCode());
        if (ToolUtil.isNotEmpty(existedMenuName)) {
            throw new VertException(BizExceptionEnum.EXISTED_THE_MENU);
        }

        //设置父级菜单编号
        menuSetPcode(menu);

        menu.setStatus(MenuStatusEnum.ENABLE.getCode());
        this.menuService.insert(menu);
        return SUCCESS_TIP;
    }

    /**
     * 删除菜单
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/remove")
    @BusinessLog(value = "删除菜单", key = "menuId", dict = MenuDict.class)
    @ResponseBody
    public Tip remove(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }

        //缓存菜单的名称
        prototypeBeanInstance.getLogSessionHolder().setObject(DaoFactory.instance().getMenuName(menuId));

        this.menuService.delMenuContainSubMenus(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 查看菜单
     */
    @RequestMapping(value = "/view/{menuId}")
    @ResponseBody
    public Tip view(@PathVariable Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new VertException(BizExceptionEnum.REQUEST_NULL);
        }
        this.menuService.selectById(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 获取菜单列表(首页用)
     */
    @RequestMapping(value = "/menuTreeList")
    @ResponseBody
    public List<ZTreeNode> menuTreeList() {
        List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
        return roleTreeList;
    }

    /**
     * 获取菜单列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectMenuTreeList")
    @ResponseBody
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/menuTreeListByRoleId/{roleId}")
    @ResponseBody
    public List<ZTreeNode> menuTreeListByRoleId(@PathVariable Integer roleId) {
        List<Long> menuIds = this.menuService.getMenuIdsByRoleId(roleId);
        if (ToolUtil.isEmpty(menuIds)) {
            List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
            return roleTreeList;
        } else {
            List<ZTreeNode> roleTreeListByUserId = this.menuService.menuTreeListByMenuIds(menuIds);
            return roleTreeListByUserId;
        }
    }

    /**
     * 根据请求的父级菜单编号设置pcode和层级
     */
    private void menuSetPcode(@Valid Menu menu) {
        if (ToolUtil.isEmpty(menu.getPCode()) || menu.getPCode().equals("0")) {
            menu.setPCode("0");
            menu.setPCodes("[0],");
            menu.setLevels(1);
        } else {
            long code = Long.parseLong(menu.getPCode());
            Menu pMenu = menuService.selectById(code);
            Integer pLevels = pMenu.getLevels();
            menu.setPCode(pMenu.getCode());

            //如果编号和父编号一致会导致无限递归
            if (menu.getCode().equals(menu.getPCode())) {
                throw new VertException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }

            menu.setLevels(pLevels + 1);
            menu.setPCodes(pMenu.getPCodes() + "[" + pMenu.getCode() + "],");
        }
    }

}
