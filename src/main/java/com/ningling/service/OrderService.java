package com.ningling.service;

import com.ningling.DTO.OrderDTO;
import com.ningling.Entity.Order;
import com.ningling.VO.OrderVO;

import java.util.List;

public interface OrderService {
    boolean orderCreate(OrderDTO orderDTO);

    List<OrderVO> getOrdersByUserId(int userId);
}
