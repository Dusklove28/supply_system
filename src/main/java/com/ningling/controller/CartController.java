package com.ningling.controller;

import com.ningling.DTO.CartAddDTO;
import com.ningling.DTO.CartUpdateQuantityDTO;
import com.ningling.Entity.ShoppingCart;
import com.ningling.VO.CartsItemVO;
import com.ningling.service.CartService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cart")
@Api(tags = "购物车接口")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(value = "/getCartsList/{userId}",produces = "application/json")
    public Result<List<CartsItemVO>> getCartsList(@PathVariable int userId){
        List<CartsItemVO> cartsLists = cartService.getCartsList(userId);
        return Result.success(cartsLists);
    }

    @PostMapping(value = "/addToCart",produces = "application/json")
    public Result addToCart(@RequestBody CartAddDTO cart){
       if(!cartService.addToCart(cart)){
           return Result.error("添加失败");
       }
        return Result.success("添加成功");
    }

    @DeleteMapping("/deleteCartItem/{userId}/{productId}")
    public Result deleteCartItem(@PathVariable int userId, @PathVariable int productId){

        cartService.deleteCartItem(userId,productId);
        return Result.success("删除成功");
    }

    @PutMapping("/updateQuantity/{userId}/{productId}")
    public Result updateQuantity(
            @PathVariable int userId,
            @PathVariable int productId,
            @RequestBody CartUpdateQuantityDTO cartUpdateQuantityDTO
            ){
        if (!cartService.update(userId,productId,cartUpdateQuantityDTO.getQuantity())) {
            Result.success("更新失败");
        }
        return Result.success("更新成功");
    }


}

