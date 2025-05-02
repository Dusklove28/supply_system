package com.ningling.mapper;

import com.ningling.Entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper {

    //查
    ShoppingCart findCartById(long userId,long productId);

    //更新
    boolean updateCartById(ShoppingCart shoppingCart);

    //创建
    boolean insertCart(ShoppingCart shoppingCart);

    @Select("select * from shopping_cart_items where user_id = #{userId}")
    List<ShoppingCart> findAll(int userId);

    int delete(int userId, int productId);
}
