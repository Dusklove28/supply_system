package com.ningling.service;

import com.ningling.DTO.UserLoginDTO;
import com.ningling.Entity.User;

public interface UserSerivice {

    public User userLogin(UserLoginDTO userLoginDTO);

}
