package com.ningling.controller;


import com.ningling.DTO.UserLoginDTO;
import com.ningling.DTO.UserPageQueryDTO;
import com.ningling.DTO.UserRegistrationDTO;
import com.ningling.Entity.User;
import com.ningling.VO.PageResult;

import com.ningling.VO.UserInfoVO;
import com.ningling.VO.UserLoginVO;

import com.ningling.properties.JwtProperties;
import com.ningling.service.UserSerivice;
import com.ningling.utils.JwtUtils;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserSerivice userSerivice;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/registration")
    @ApiOperation("用户注册")
    public Result<?> registration(@RequestBody UserRegistrationDTO userRegistrationDTO){
        if(!userSerivice.registration(userRegistrationDTO)){
            return Result.error("注册失败");
        }
        return Result.success("注册成功");
    }
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
        userLoginVO.setUsername(user.getUsername());
        userLoginVO.setId(user.getUserId());
        userLoginVO.setName(user.getName());
        userLoginVO.setToken(token);
        //前端要求roles必须是数组，封装一下
        String roles = user.getRoles();
        //示例数据:["admin,customers"]
        //分割后["admin","customer"}
        List<String> rolesAfterSplit = Arrays.asList(roles.split(","));
        userLoginVO.setRoles(rolesAfterSplit);

        return Result.success(userLoginVO);
    }

    @GetMapping("/getUserById/{userId}")
    @ApiOperation("用户查询")
    public Result<UserInfoVO> getUserById(@PathVariable Long userId){
        UserInfoVO user = userSerivice.getUserById(userId);
        if(user == null){
            return Result.error("用户信息为空");
        }
        return Result.success(user);
    }

    @PostMapping("/logout")
    @ApiOperation("用户注销")
    public Result<Object> logout(){
        return Result.success();
    }

    @PostMapping("/getUsersList")
    @ApiOperation("用户分页查询")
    public Result<PageResult> getUsersList(@RequestBody UserPageQueryDTO upd){
        PageResult pageQuery = userSerivice.getPageQuery(upd);
        return Result.success(pageQuery);
    }

    @PutMapping("/updateUser")
    @ApiOperation("修改用户")
    public Result updateUserInfo(@RequestBody User user){
        userSerivice.updateUserInfo(user);
        return Result.success("修改成功");
    }

    @DeleteMapping("/deleteUser/{userId}/{productId}")
    @ApiOperation("用户删除")
    public Result deleteUserByUserId(@PathVariable int userId,@PathVariable int productId){
    //数据库表好多外键限制，还是先写其他的接口
    //        userService.deleteUserByUserId(userId,productId)
        return Result.success("模拟删除成功");
    }

}
