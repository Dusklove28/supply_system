package com.ningling.service.impl;

import com.ningling.DTO.OrderDTO;
import com.ningling.Entity.Order;
import com.ningling.Entity.OrderDetail;
import com.ningling.Entity.Pears;
import com.ningling.Entity.User;
import com.ningling.VO.OrderVO;
import com.ningling.VO.OrdersForAdminVO;
import com.ningling.VO.PearsListVO;
import com.ningling.mapper.OrderMapper;
import com.ningling.mapper.PearsMapper;
import com.ningling.mapper.UserMapper;
import com.ningling.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PearsMapper pearsMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean orderCreate(OrderDTO orderDTO) {
        Order order = Order.builder()
                .userId(orderDTO.getUserId())
                .totalAmount(orderDTO.getTotalAmount())
                .status(orderDTO.getStatus())
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
        int order1 = orderMapper.createOrder(order);
        if (order1 <= 0) {
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


        if (orderMapper.createOrderDetail(orderDetail) <= 0) {
            throw new IllegalArgumentException("订单详情更新异常");
        }

        return true;
    }


    @Override
    public List<OrderVO> getOrdersByUserId(int userId) {
        //查订单ID
        List<Order> orders = orderMapper.getOrders(userId);
        //查订单详情
        List<OrderDetail> orderDetail = new ArrayList<>();
        //封装到VO
        List<OrderVO> orderVOS = new ArrayList<>();
        for (Order o : orders) {
            OrderDetail od = orderMapper.getOrderDetail(o.getOrderId());
            //查询商品信息
            Pears p = pearsMapper.getPearById(od.getProductId());

            PearsListVO pV = PearsListVO.builder()
                    .id(p.getProductId())
                    .name(p.getName())
                    .description(p.getDescription())
                    .price(p.getPrice())
                    .imgUrl(p.getImageUrl())
                    .build();

            orderDetail.add(od);

            OrderVO orderVO = OrderVO.builder()
                    .orderId(o.getOrderId())
                    .userId(o.getUserId())
                    .totalAmount(o.getTotalAmount())
                    .status(o.getStatus())
                    .createdTime(o.getCreatedTime())
                    .procutId(od.getProductId())
                    .quantity(od.getQuantity())
                    .price(od.getPrice())
                    .address(od.getAddress())
                    .remark(od.getRemark())
                    .items(pV)
                    .build();
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }


    @Override
    public boolean updateOrder(OrderDTO orderDTO) {
        //更新status
        if(orderMapper.updateOrder(orderDTO) <= 0){
            return false;
        }
        return true;
    }

    @Override
    public List<OrdersForAdminVO> getOrders() {
        //查订单ID
        List<Order> orders = orderMapper.getAllOrders();
        //查订单详情
        List<OrderDetail> orderDetailList = new ArrayList<>();
        //封装到VO
        List<OrdersForAdminVO> orderVOS = new ArrayList<>();
        for(Order o : orders){
            OrderDetail od = orderMapper.getOrderDetail(o.getOrderId());
            //查询商品信息
            Pears p = pearsMapper.getPearById(od.getProductId());
            User user = userMapper.getUserById(o.getUserId());
            PearsListVO pV = PearsListVO.builder()
                    .id(p.getProductId())
                    .name(p.getName())
                    .description(p.getDescription())
                    .price(p.getPrice())
                    .imgUrl(p.getImageUrl())
                    .build();

            orderDetailList.add(od);

            OrdersForAdminVO orderVO = OrdersForAdminVO.builder()
                    .orderId(od.getOrderId())
                    .userId(o.getUserId())
                    .productId(od.getProductId())
                    .productName(p.getName())
                    .username(user.getUsername())
                    .totalAmount(o.getTotalAmount())
                    .status(o.getStatus())
                    .quantity(od.getQuantity())
                    .price(od.getPrice())
                    .address(od.getAddress())
                    .remark(od.getRemark())
                    .createdTime(o.getCreatedTime())
                    .updatedTime(o.getUpdatedTime())
                    .items(pV)
                    .build();


            orderVOS.add(orderVO);
        }
        return orderVOS;
    }
}
