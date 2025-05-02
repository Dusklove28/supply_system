package com.ningling.service.impl;

import com.ningling.DTO.OrderDTO;
import com.ningling.Entity.Order;
import com.ningling.Entity.OrderDetail;
import com.ningling.mapper.OrderMapper;
import com.ningling.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public boolean orderCreate(OrderDTO orderDTO) {
        Order order = Order.builder()
                .userId(orderDTO.getUserId())
                .totalAmount(orderDTO.getTotalAmount())
                .status(0)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
        int order1 = orderMapper.createOrder(order);
        if(order1<= 0){
            throw new IllegalArgumentException("订单创建异常");
        }
        OrderDetail orderDetail = OrderDetail.builder()
                //主键回填
                .orderId(order.getOrderId())
                .productId(orderDTO.getProductId())
                .quantity(orderDTO.getQuantity())
                .price(orderDTO.getPrice())
                .address(orderDTO.getAddress())
                .remark(orderDTO.getRemark())
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();



        if(orderMapper.createOrderDetail(orderDetail) <= 0){
            throw new IllegalArgumentException("订单详情更新异常");
        }

        return true;
    }
}
