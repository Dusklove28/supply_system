package com.ningling.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询后返回的用户数据格式")
public class UserInfoVO {

    @ApiModelProperty("用户id")
    private long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("用户名")
    private String userName;

//    private String password;//密码
    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private int sex;

//TODO    private String img;//头像地址

    @ApiModelProperty("用户角色")
    private List<String> roles;//用户权限


    @ApiModelProperty("地址")
    private String address;//地址

    @ApiModelProperty("状态")
    private int status;//状态

//    @ApiModelProperty("创建时间")
//    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createTime;
//
//    @ApiModelProperty("更新时间")
//    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime updateTime;
}
