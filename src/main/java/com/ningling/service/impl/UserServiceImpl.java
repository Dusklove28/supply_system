package com.ningling.service.impl;

import com.ningling.DTO.UserLoginDTO;
import com.ningling.Entity.User;
import com.ningling.globalException.CustomExceptionsConstant;
import com.ningling.globalException.PasswordException;
import com.ningling.mapper.UserMapper;
import com.ningling.service.UserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class UserServiceImpl implements UserSerivice {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(UserLoginDTO userLoginDTO) {

        //获取用户名并根据用户名验证用户密码
        String userName = userLoginDTO.getUserName();
        String password = userLoginDTO.getPassword();
        User user = userMapper.getByUserName(userName);
        //使用MD5解密并比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //1密码错误
        if(!user.getPassword().equals(password)){
            //抛出密码异常
            throw new PasswordException(CustomExceptionsConstant.PASSWORD_ERROR);
        }
        //2账户不存在
        if(user==null){
            throw new PasswordException(CustomExceptionsConstant.ACCOUNT_NOT_FOUND);
        }
        //否则匹配成功

        return user;
    }

}
