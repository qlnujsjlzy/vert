package com.zhaoyang.vert.module.system.factory;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhaoyang.vert.core.cache.CacheKey;
import com.zhaoyang.vert.core.common.constant.enums.ManagerStatusEnum;
import com.zhaoyang.vert.core.common.constant.enums.MenuStatusEnum;
import com.zhaoyang.vert.core.shiro.ShiroUser;
import com.zhaoyang.vert.core.util.ConvertUtil;
import com.zhaoyang.vert.core.util.ToolUtil;
import com.zhaoyang.vert.module.system.dao.*;
import com.zhaoyang.vert.module.system.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * dao层 静态工厂类
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@Slf4j
@Component
public class DaoFactory {


    @Resource
    private RoleMapper roleMapper;
    @Resource
    private DeptMapper deptMapper;
    @Resource
    private DictMapper dictMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private NoticeMapper noticeMapper;


    private static DaoFactory daoFactory;

    public static DaoFactory instance() {
        return daoFactory;
    }

    @PostConstruct
    private void init() {
        daoFactory = this;
        daoFactory.roleMapper = this.roleMapper;
        daoFactory.deptMapper = this.deptMapper;
        daoFactory.dictMapper = this.dictMapper;
        daoFactory.userMapper = this.userMapper;
        daoFactory.menuMapper = this.menuMapper;
        daoFactory.noticeMapper = this.noticeMapper;
    }


    /**
     * 根据用户id获取用户名称
     */

    public String getUserNameById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    /**
     * 根据用户id获取用户账号
     */

    public String getUserAccountById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }


    /**
     * 通过角色ids获取角色名称
     */

    @Cacheable(value = CacheKey.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    public String getRoleName(String roleIds) {
        Integer[] roles = ConvertUtil.toIntArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (int role : roles) {
            Role roleObj = roleMapper.selectById(role);
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StringUtils.strip(sb.toString(), ",");
    }

    /**
     * 通过角色id获取角色名称
     */

    @Cacheable(value = CacheKey.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(Integer roleId) {
        if (roleId == null || roleId.equals(0)) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     */

    @Cacheable(value = CacheKey.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(Integer roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "";
    }

    /**
     * 获取部门名称
     */

    @Cacheable(value = CacheKey.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(Integer deptId) {
        Dept dept = deptMapper.selectById(deptId);
        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullName())) {
            return dept.getFullName();
        }
        return "";
    }

    /**
     * 获取菜单的名称们(多个)
     */

    public String getMenuNames(String menuIds) {
        Integer[] menus = ConvertUtil.toIntArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (int menu : menus) {
            Menu menuObj = menuMapper.selectById(menu);
            if (ToolUtil.isNotEmpty(menuObj) && ToolUtil.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StringUtils.strip(sb.toString(), ",");
    }

    /**
     * 获取菜单名称
     */

    public String getMenuName(Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        }
        Menu menu = menuMapper.selectById(menuId);
        if (menu == null) {
            return "";
        } else {
            return menu.getName();

        }
    }

    /**
     * 获取菜单名称通过编号
     */

    public String getMenuNameByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return "";
        }
        Menu param = new Menu();
        param.setCode(code);
        Menu menu = menuMapper.selectOne(param);
        if (menu == null) {
            return "";
        } else {
            return menu.getName();
        }

    }

    /**
     * 获取字典名称
     */

    public String getDictName(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        }
        Dict dict = dictMapper.selectById(dictId);
        if (dict == null) {
            return "";
        } else {
            return dict.getName();
        }

    }

    /**
     * 获取通知标题
     */

    public String getNoticeTitle(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        }
        Notice notice = noticeMapper.selectById(dictId);
        if (notice == null) {
            return "";
        } else {
            return notice.getTitle();
        }

    }

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */

    public String getDictsByName(String name, Integer val) {
        Dict temp = new Dict();
        temp.setName(name);
        Dict dict = dictMapper.selectOne(temp);
        if (dict == null) {
            return "";
        }
        Wrapper<Dict> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("pid", dict.getId());
        List<Dict> dicts = dictMapper.selectList(wrapper);
        for (Dict item : dicts) {
            if (item.getNum() != null && item.getNum().equals(val)) {
                return item.getName();
            }
        }
        return "";
    }

    /**
     * 获取性别名称
     */

    public String getSexName(Integer sex) {
        return getDictsByName("性别", sex);
    }

    /**
     * 获取用户登录状态
     */

    public String getStatusName(Integer status) {
        return ManagerStatusEnum.valueOf(status);
    }

    /**
     * 获取菜单状态
     */

    public String getMenuStatusName(Integer status) {
        return MenuStatusEnum.valueOf(status);
    }

    /**
     * 查询字典
     */

    public List<Dict> findInDict(Integer id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        }
        EntityWrapper<Dict> wrapper = new EntityWrapper<>();
        List<Dict> dicts = dictMapper.selectList(wrapper.eq("pid", id));
        if (dicts == null || dicts.size() == 0) {
            return null;
        } else {
            return dicts;
        }

    }


    /**
     * 获取子部门id
     */

    public List<Integer> getSubDeptId(Integer deptid) {
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + deptid + "]%");
        List<Dept> depts = this.deptMapper.selectList(wrapper);

        ArrayList<Integer> deptids = new ArrayList<>();

        if (depts != null && depts.size() > 0) {
            for (Dept dept : depts) {
                deptids.add(dept.getId());
            }
        }

        return deptids;
    }

    /**
     * 获取所有父部门id
     */

    public List<Integer> getParentDeptIds(Integer deptid) {
        Dept dept = deptMapper.selectById(deptid);
        String pids = dept.getPIds();
        String[] split = pids.split(",");
        ArrayList<Integer> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Integer.valueOf(StringUtils.strip(StringUtils.strip(s, "["), "]")));
        }
        return parentDeptIds;
    }

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    public User user(String account) {
        User user = userMapper.getByAccount(account);
        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (!ManagerStatusEnum.OK.getCode().equals(user.getStatus())) {
            throw new LockedAccountException();
        }
        return user;
    }

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user 系统用户
     */
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());
        shiroUser.setAccount(user.getAccount());
        shiroUser.setDeptId(user.getDeptId());
        shiroUser.setDeptName(DaoFactory.instance().getDeptName(user.getDeptId()));
        shiroUser.setName(user.getName());

        Integer[] roleArray = ConvertUtil.toIntArray(user.getRoleId());
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (int roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(DaoFactory.instance().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

    /**
     * 获取权限列表通过角色id
     *
     * @param roleId 角色id
     */
    public List<String> findPermissionsByRoleId(Integer roleId) {
        return menuMapper.getResUrlsByRoleId(roleId);
    }

    /**
     * 根据角色id获取角色名称
     *
     * @param roleId 角色id
     */
    public String findRoleNameByRoleId(Integer roleId) {
        return DaoFactory.instance().getSingleRoleTip(roleId);
    }


}
