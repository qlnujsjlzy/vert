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
 * 登录记录
 *
 * @author : zhaoyang.li
 * @date : 2018/5/7
 */
@TableName("sys_login_log")
@Setter
@Getter
@ToString
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 日志名称
     */
    private String logName;

    /**
     * 管理员id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否执行成功
     */
    private String succeed;
    /**
     * 具体消息
     */
    private String message;
    /**
     * 登录ip
     */
    private String ip;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public LoginLog(String logName, Integer userId, String succeed, String message, String ip) {
        this.logName = logName;
        this.userId = userId;
        this.createTime = new Date();
        this.succeed = succeed;
        this.message = message;
        this.ip = ip;
    }
}
