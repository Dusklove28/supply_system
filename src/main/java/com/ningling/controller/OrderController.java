package com.ningling.controller;

import com.ningling.DTO.OrderDTO;
import com.ningling.Entity.Order;
import com.ningling.VO.OrderVO;
import com.ningling.VO.OrdersForAdminVO;
import com.ningling.service.OrderService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Api(tags = "订单相关接口")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @ApiOperation("创建订单")
    public Result orderCreate(@RequestBody OrderDTO orderDTO){

        if(!orderService.orderCreate(orderDTO)){
            return Result.error("订单创建成功");
        }
        return Result.success("订单创建成功");
    }

    @GetMapping("/getOrdersById/{userId}")
    @ApiOperation("查询订单")
    public Result<List<OrderVO>> getOrdersList(@PathVariable int userId){
        List<OrderVO> ordersByUserId = orderService.getOrdersByUserId(userId);
        return Result.success(ordersByUserId);
    }


    @PutMapping("/updataOrders")
    @ApiOperation("更新订单")
    public Result updateOrder(@RequestBody OrderDTO orderDTO){
        if(!orderService.updateOrder(orderDTO)){
            return Result.success("更新失败");
        }
        return Result.success("更新成功");
    }

    @GetMapping("/getAllOrders")
    @ApiOperation("查询所有订单")
    public Result<List<OrdersForAdminVO>> getAllOrders(){
        List<OrdersForAdminVO> orders = orderService.getOrders();
        return Result.success(orders);
    }
}
