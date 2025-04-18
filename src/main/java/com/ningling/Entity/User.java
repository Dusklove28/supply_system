package com.ningling.Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private long id;//用户id

    private String name;//姓名

    private String userName;//用户名

    private String password;//密码

    private String phone;//手机号

    private int sex;//性别

//TODO    private String img;//头像地址

    private int roles;//用户权限

    private String idCard;//身份证号

    private String address;//地址

    private int status;//状态

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
