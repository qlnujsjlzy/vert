package com.zhaoyang.vert.module.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @author : zhaoyang.li
 * @date : 2018/5/8
 */
@TableName("sys_operation_log")
@Getter
@Setter
@ToString
public class OperationLog extends Model<OperationLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 日志类型
     */
    private String logType;
    /**
     * 日志名称
     */
    private String logName;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 类名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否成功
     */
    private String succeed;
    /**
     * 备注
     */
    private String message;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
