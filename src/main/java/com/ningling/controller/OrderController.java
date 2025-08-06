package com.ningling.controller;

import com.ningling.DTO.OrderClearDTO;
import com.ningling.DTO.OrderDTO;
import com.ningling.DTO.PaymentDTO;
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
    public Result<OrderVO> orderCreate(@RequestBody OrderDTO orderDTO){

        OrderVO orderVO = orderService.orderCreate(orderDTO);
        if(orderVO!=null){
            return Result.success(orderVO);
        }
        return Result.error("订单创建失败");
    }

    @GetMapping("/getOrdersById/{userId}")
    @ApiOperation("查询订单")
    public Result<List<OrderVO>> getOrdersList(@PathVariable int userId){
        List<OrderVO> ordersByUserId = orderService.getOrdersByUserId(userId);
        return Result.success(ordersByUserId);
    }


    @PutMapping("/updateOrders")
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

    @DeleteMapping("/deleteOrder/{orderId}")
    @ApiOperation("查询所有订单")
    public Result<List<OrdersForAdminVO>> getAllOrders(@PathVariable Long orderId){
        if(!orderService.deleteOrder(orderId)){
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }


    @PostMapping("/payment")
    @ApiOperation("订单支付")
    public Result<?> payment(@RequestBody PaymentDTO paymentDTO){
        if(!orderService.payment(paymentDTO)){
            return Result.error("支付失败");
        }
        return Result.success("支付成功");
    }

    @PostMapping("/orderClear")
    @ApiOperation("结算订单")
    public Result<List<OrderVO>> orderClear(@RequestBody OrderClearDTO orderClearDTO){

        List<OrderVO> orderVOS = orderService.orderClear(orderClearDTO);
        if(orderVOS == null){
            return Result.error("结算失败");
        }
        return Result.success(orderVOS);
    }


}
