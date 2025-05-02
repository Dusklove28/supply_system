package com.ningling.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserPageQueryVO {
    //驼峰命名，需要支持驼峰命名和下划线命名之间的自动映射
    private long userId;//用户id

    private String name;//姓名

    private String username;//用户名

    private String password;//密码

    private String phone;//手机号

    private String sex;//性别

//TODO    private String avatar;//头像地址

    private String roles;//用户权限


    private String address;//地址

//    private int status;//状态

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;
}
