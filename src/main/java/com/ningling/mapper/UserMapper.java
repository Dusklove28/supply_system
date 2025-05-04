package com.ningling.mapper;

import com.github.pagehelper.Page;
import com.ningling.Entity.User;
import com.ningling.VO.PageResult;
import com.ningling.VO.UserPageQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from users where username = #{username}")
    User getByUsername(String username);

    @Select("select * from users where user_id = #{userId}")
    User getUserById(Long userId);


    Page<UserPageQueryVO> pageQueryUsers(String username);

    void delete(int userId, int productId);

    int update(User userId);
}
