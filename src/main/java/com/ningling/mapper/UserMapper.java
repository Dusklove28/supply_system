package com.ningling.mapper;

import com.github.pagehelper.Page;
import com.ningling.Entity.User;
import com.ningling.VO.UserPageQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from users where username = #{username}")
    User getByUsername(String username);

    @Select("select * from users where user_id = #{userId}")
    User getUserById(Long userId);

    @Select("select * from users")
    Page<UserPageQueryVO> pageQueryUsers();

    void delete(int userId, int productId);
}
