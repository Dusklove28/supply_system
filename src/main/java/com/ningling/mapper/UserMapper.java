package com.ningling.mapper;

import com.ningling.DTO.UserDTO;
import com.ningling.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from user where username = #{username}")
    public User getByUserName(String username);
}
