package com.ningling.service.impl;

import com.ningling.DTO.OrderClearDTO;
import com.ningling.DTO.OrderDTO;
import com.ningling.DTO.PaymentDTO;
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
import com.ningling.service.PearsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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


    @Autowired
    private PearsService pearsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO orderCreate(OrderDTO orderDTO) {


        Order order = null;
        try {
            order = Order.builder()
                    .userId(orderDTO.getUserId())
                    .totalAmount(orderDTO.getTotalAmount())
                    .status(orderDTO.getStatus())
                    .createdTime(LocalDateTime.now())
                    .updatedTime(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

        //扣减库存

        if (!pearsService.checkStockById(orderDTO.getProductId())) {
            Pears pears = new Pears();
            pears.setProductId(orderDetail.getProductId());
            int stock = pears.getStock();
            stock = stock - 1;
            pears.setStock(stock);
            pearsService.update(pears, orderDTO.getProductId());
        }

        if (orderMapper.createOrderDetail(orderDetail) <= 0) {
            throw new IllegalArgumentException("订单详情创建异常");
        }
        OrderVO orderVO = OrderVO.builder()
                .orderId(order.getOrderId())
                .userId(orderDTO.getUserId())
                .productId(orderDTO.getProductId())
                .totalAmount(orderDTO.getTotalAmount())
                .status(orderDTO.getStatus())
                .quantity(orderDTO.getQuantity())
                .price(orderDTO.getPrice())
                .address(orderDTO.getAddress())
                .remark(orderDTO.getRemark())
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();

        return orderVO;
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
                    .productId(od.getProductId())
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
        //更新status和地址
        Order order = Order.builder()
                .orderId(orderDTO.getOrderId())
                .userId(orderDTO.getUserId())
                .totalAmount(orderDTO.getTotalAmount())
                .status(0)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();

        if (orderMapper.updateOrder(order) <= 0) {
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
        for (Order o : orders) {
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOrder(Long orderId) {
        try {
            // 1. 先删除订单详情（子表）
            int detailsDeleted = orderMapper.deleteOrderDetail(orderId);
            // 2. 再删除订单主表（父表）
            int orderDeleted = orderMapper.deleteOrder(orderId);

            // 3. 验证操作结果
            if (orderDeleted < 1) {
                throw new RuntimeException("订单不存在或已被删除");
            }
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("订单删除失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public boolean payment(PaymentDTO paymentDTO) {

        List<PaymentDTO.OrderPayment> orders = paymentDTO.getOrders();

        for(PaymentDTO.OrderPayment o : orders){
            Long orderId = o.getOrderId();
            Order order = new Order();
            order.setOrderId(orderId);
            order.setStatus(1);
            if (orderMapper.updateOrder(order) < 0) {
                return false;
            }
        }

        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrderVO> orderClear(OrderClearDTO orderClearDTO) {


        List<OrderDTO> items = orderClearDTO.getItems();

        List<OrderVO> orderVOS = new ArrayList<>();
        for (OrderDTO orderDTO : items) {

            Order order = Order.builder()
                    .userId(orderClearDTO.getUserId())
                    //订单表存储一笔订单价格
                    .totalAmount(orderDTO.getPrice())
                    .status(orderDTO.getStatus())
                    .createdTime(LocalDateTime.now())
                    .updatedTime(LocalDateTime.now())
                    .build();

            //出错了
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

            //扣减库存
            if(!pearsService.checkStockById(orderDTO.getProductId())){
                Pears pears = new Pears();
                pears.setProductId(orderDetail.getProductId());
                int stock = pears.getStock();
                stock = stock-1;
                pears.setStock(stock);
                pearsService.update(pears,orderDTO.getProductId());
            }

            if (orderMapper.createOrderDetail(orderDetail) <= 0) {
                throw new IllegalArgumentException("订单详情创建异常");
            }
            OrderVO orderVO = OrderVO.builder()
                    .orderId(order.getOrderId())
                    .userId(orderDTO.getUserId())
                    .productId(orderDTO.getProductId())
                    .totalAmount(orderDTO.getTotalAmount())
                    .status(orderDTO.getStatus())
                    .quantity(orderDTO.getQuantity())
                    .price(orderDTO.getPrice())
                    .address(orderDTO.getAddress())
                    .remark(orderDTO.getRemark())
                    .createdTime(LocalDateTime.now())
                    .updatedTime(LocalDateTime.now())
                    .build();

            orderVOS.add(orderVO);
        }

        return orderVOS;
    }

}
