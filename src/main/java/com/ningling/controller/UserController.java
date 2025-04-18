package com.ningling.controller;

import com.ningling.DTO.UserLoginDTO;
import com.ningling.Entity.User;
import com.ningling.VO.UserLoginVO;
import com.ningling.properties.JwtProperties;
import com.ningling.service.UserSerivice;
import com.ningling.utils.JwtUtils;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserSerivice userSerivice;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping(value = "/login",produces = "application/json")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {

        //查询数据库
        User user = userSerivice.userLogin(userLoginDTO);


        //登陆成功，返回数据
        Map<String, String> claims = new HashMap<>();

        try {
            String pwd = claims.put("pwd", user.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String token = JwtUtils.getToken(jwtProperties.getSecret_key(), jwtProperties.getTtl(), claims);

        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setUserName(user.getUserName());
        userLoginVO.setId(user.getId());
        userLoginVO.setName(user.getName());
        userLoginVO.setToken(token);

        return Result.success(userLoginVO);
    }
}
