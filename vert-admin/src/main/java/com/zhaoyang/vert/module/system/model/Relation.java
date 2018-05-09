package com.zhaoyang.vert.module.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色和菜单关联表
 *
 * @author : zhaoyang.li
 * @date : 2018/5/9
 */
@TableName("sys_relation")
@Setter
@Getter
@ToString
public class Relation extends Model<Relation> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单id {@link com.zhaoyang.vert.module.system.model.Role}
     */
    private Long menuId;
    /**
     * 角色id {@link com.zhaoyang.vert.module.system.model.Role}
     */
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
