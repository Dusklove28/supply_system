package com.ningling.controller;

import com.ningling.DTO.UserLoginDTO;
import com.ningling.DTO.UserPageQueryDTO;
import com.ningling.Entity.User;
import com.ningling.VO.PageResult;
import com.ningling.VO.UserInfoVO;
import com.ningling.VO.UserLoginVO;
import com.ningling.VO.UserPageQueryVO;
import com.ningling.properties.JwtProperties;
import com.ningling.service.UserSerivice;
import com.ningling.utils.JwtUtils;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        userLoginVO.setUsername(user.getUsername());
        userLoginVO.setId(user.getUserId());
        userLoginVO.setName(user.getName());
        userLoginVO.setToken(token);
        //前端要求roles必须是数组，封装一下
        ArrayList<String> roles = new ArrayList<>();
        roles.add(user.getRoles());
        userLoginVO.setRoles(roles);

        return Result.success(userLoginVO);
    }

    @GetMapping("/getUserById/{userId}")
    @ApiOperation("用户查询")
    public Result<User> getUserById(@PathVariable Long userId){
        User user = userSerivice.getUserById(userId);
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


    @DeleteMapping("/deleteUser/{userId}/{productId}")
    @ApiOperation("用户删除")
    public Result deleteUserByUserId(@PathVariable int userId,@PathVariable int productId){
    //数据库表好多外键限制，还是先写其他的接口
//        userSerivice.deleteUserByUserId(userId,productId)
        return Result.success("模拟删除成功");
    }
}
