package com.ningling.mapper;

import com.ningling.Entity.Order;
import com.ningling.Entity.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {


    int createOrder(Order order);
    @Insert("INSERT INTO order_details (order_id,product_id,quantity, price,address,remark,created_time, updated_time) " +
            "VALUES (#{orderId}, #{productId},#{quantity},#{price},#{address},#{remark}, #{createdTime}, #{updatedTime})")
    int createOrderDetail(OrderDetail orderDetail);
}
