package com.ningling.service;

import com.ningling.DTO.UserLoginDTO;
import com.ningling.DTO.UserPageQueryDTO;
import com.ningling.Entity.User;
import com.ningling.VO.PageResult;
import com.ningling.VO.UserPageQueryVO;

import java.util.List;

public interface UserSerivice {

    public User userLogin(UserLoginDTO userLoginDTO);

    User getUserById(Long id);

    PageResult getPageQuery(UserPageQueryDTO upd);

    void deleteUserByUserId(int userId, int productId);
}
