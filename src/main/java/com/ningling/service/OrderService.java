package com.ningling.service;

import com.ningling.DTO.OrderClearDTO;
import com.ningling.DTO.OrderDTO;
import com.ningling.DTO.PaymentDTO;
import com.ningling.Entity.Order;
import com.ningling.VO.OrderVO;
import com.ningling.VO.OrdersForAdminVO;

import java.util.List;

public interface OrderService {
    OrderVO orderCreate(OrderDTO orderDTO);

    List<OrderVO> getOrdersByUserId(int userId);

    boolean updateOrder(OrderDTO orderDTO);

    List<OrdersForAdminVO> getOrders();

    boolean deleteOrder(Long orderId);

    boolean payment(PaymentDTO paymentDTO);

    List<OrderVO> orderClear(OrderClearDTO orderClearDTO);

}
