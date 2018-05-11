package com.zhaoyang.vert.module.system.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户传输bean
 *
 * @author : zhaoyang.li
 * @date : 2018/5/11
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String account;
    private String password;
    private String salt;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private String roleId;
    private Integer deptId;
    private Integer status;
    private Date createTime;
    private Integer version;
    private String avatar;
}
