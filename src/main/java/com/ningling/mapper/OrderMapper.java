package com.ningling.mapper;

import com.ningling.DTO.OrderDTO;
import com.ningling.Entity.Order;
import com.ningling.Entity.OrderDetail;
import org.apache.ibatis.annotations.Delete;
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

    int updateOrder(Order order);

    int updateOrderDetail(OrderDetail orderDetail);

    @Select("select * from orders")
    List<Order> getAllOrders();

    @Delete("delete from orders where orderId = #{orderId}")
    int deleteOrder(Long orderId);

    @Delete("delete from orders_details where orderId  = #{orderId}")
    int deleteOrderDetail(Long orderId);
}
