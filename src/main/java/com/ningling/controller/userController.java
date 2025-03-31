package com.ningling.controller;

import com.ningling.DTO.userLoginDTO;
import com.ningling.VTO.userLoginVO;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class userController {


    @PostMapping(value = "/login",produces = "application/json")
    @ApiOperation("用户登录")
    public Result<userLoginVO> login(userLoginDTO userLoginDTO) {

        //测试
        userLoginVO test = new userLoginVO();
        test.setId(1L);
        test.setUserName("Dusklove28");
        test.setName("袁xx");


        return Result.success(test);
    }
}
