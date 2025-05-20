package com.ningling.service;

import com.ningling.DTO.OrderDTO;
import com.ningling.Entity.Order;
import com.ningling.VO.OrderVO;
import com.ningling.VO.OrdersForAdminVO;

import java.util.List;

public interface OrderService {
    boolean orderCreate(OrderDTO orderDTO);

    List<OrderVO> getOrdersByUserId(int userId);

    boolean updateOrder(OrderDTO orderDTO);

    List<OrdersForAdminVO> getOrders();
}
