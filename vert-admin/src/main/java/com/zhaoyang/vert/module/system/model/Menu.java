package com.zhaoyang.vert.module.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 菜单表
 *
 * @author : zhaoyang.li
 * @date : 2018/5/9
 */
@TableName("sys_menu")
@Setter
@Getter
@ToString
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 菜单编号
     */
    private String code;
    /**
     * 菜单父编号
     */
    private String parentCode;
    /**
     * 当前菜单的所有父菜单编号
     */
    private String parentCodes;
    /**
     * 菜单名称
     */
    @NotBlank
    private String name;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * url地址
     */
    @NotBlank
    private String url;
    /**
     * 菜单排序号
     */
    private Integer num;
    /**
     * 菜单层级
     */
    private Integer levels;
    /**
     * 是否是菜单（1：是  0：不是）
     */
    private Integer isMenu;
    /**
     * 备注
     */
    private String tips;
    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    private Integer status;
    /**
     * 是否打开:    1:打开   0:不打开
     */
    private Integer isOpen;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }



}
