package com.ningling.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录后返回的数据格式")
public class UserLoginVO implements Serializable{

        @ApiModelProperty("主键值")
        private Long id;

        @ApiModelProperty("姓名")
        private String name;

        @ApiModelProperty("用户名")
        private String username;

        @ApiModelProperty("jwt令牌")
        private String token;

        @ApiModelProperty("角色")
        private List<String> roles;
}
