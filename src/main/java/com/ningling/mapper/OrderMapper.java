package com.ningling.mapper;

import com.ningling.Entity.Order;
import com.ningling.Entity.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {


    int createOrder(Order order);
    @Insert("INSERT INTO order_details (order_id,product_id,quantity, price,address,remark,created_time, updated_time) " +
            "VALUES (#{orderId}, #{productId},#{quantity},#{price},#{address},#{remark}, #{createdTime}, #{updatedTime})")
    int createOrderDetail(OrderDetail orderDetail);

    @Select("select * from orders where user_id = #{userId}")
    List<Order> getOrders(int userId);


    @Select("select * from order_details where order_id = #{orderId}")
    OrderDetail getOrderDetail(long orderId);
}
