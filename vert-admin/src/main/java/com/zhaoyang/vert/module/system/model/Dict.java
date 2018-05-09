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
 * 字典表
 *
 * @author : zhaoyang.li
 * @date : 2018/5/9
 */
@TableName("sys_dict")
@Setter
@Getter
@ToString
public class Dict extends Model<Dict> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 排序
     */
    private Integer num;
    /**
     * 父级字典
     */
    private Integer parentId;
    /**
     * 名称
     */
    private String name;
    /**
     * 提示
     */
    private String tips;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
