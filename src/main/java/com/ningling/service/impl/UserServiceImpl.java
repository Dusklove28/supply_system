package com.ningling.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ningling.DTO.UserLoginDTO;
import com.ningling.DTO.UserPageQueryDTO;
import com.ningling.Entity.User;
import com.ningling.VO.PageResult;
import com.ningling.VO.UserPageQueryVO;
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
        String userName = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        User user = userMapper.getByUsername(userName);
        //账户不存在
        if(user==null){
            throw new PasswordException(CustomExceptionsConstant.ACCOUNT_NOT_FOUND);
        }
        //使用MD5解密并比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //密码错误
        if(!user.getPassword().equals(password)){
            //抛出密码异常
            throw new PasswordException(CustomExceptionsConstant.PASSWORD_ERROR);
        }
        //否则匹配成功
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public PageResult getPageQuery(UserPageQueryDTO upd) {
        PageHelper.startPage(upd.getPageNum(), upd.getPageSize());
        Page<UserPageQueryVO> page = userMapper.pageQueryUsers();
        PageResult pageResult = PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
        return pageResult;
    }

    @Override
    public void deleteUserByUserId(int userId, int productId) {

        userMapper.delete(userId,productId);
    }
}
