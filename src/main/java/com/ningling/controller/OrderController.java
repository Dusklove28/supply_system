package com.ningling.controller;

import com.ningling.DTO.OrderDTO;
import com.ningling.service.OrderService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
