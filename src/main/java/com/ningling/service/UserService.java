package com.ningling.service;

import com.ningling.DTO.UserLoginDTO;
import com.ningling.DTO.UserPageQueryDTO;
import com.ningling.DTO.UserRegistrationDTO;
import com.ningling.Entity.User;
import com.ningling.VO.PageResult;
import com.ningling.VO.UserInfoVO;



public interface UserService {

    User userLogin(UserLoginDTO userLoginDTO);

    UserInfoVO getUserById(Long id);

    PageResult getPageQuery(UserPageQueryDTO upd);

    boolean deleteUserByUserId(int userId, int productId);

    void updateUserInfo(User user);

    boolean registration(UserRegistrationDTO userRegistrationDTO);

}
