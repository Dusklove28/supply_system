package com.ningling.service.impl;

import com.ningling.DTO.CartAddDTO;
import com.ningling.DTO.CartUpdateQuantityDTO;
import com.ningling.Entity.ShoppingCart;
import com.ningling.VO.CartsItemVO;
import com.ningling.mapper.CartMapper;
import com.ningling.service.CartService;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartMapper cartMapper;
    @Override
    public Boolean addToCart(CartAddDTO c) {
        //DTO转化为实体类
        ShoppingCart sc = ShoppingCart.builder()
                .userId(c.getUserId())
                .productId(c.getProductId())
                .name(c.getName())
                .quantity(c.getQuantity())
                .price(c.getPrice())
                .imageUrl(c.getImgUrl())

                .build();
        //如果购物车为空
        ShoppingCart cartInfo = cartMapper.findCartById(c.getUserId(), c.getProductId());
        if(cartInfo == null ){
            //创建
            sc.setCreatedTime(LocalDateTime.now());
            cartMapper.insertCart(sc);
            return true;
        }
        //不为空
        //更新
        if(!cartMapper.updateCartById(sc)){
             sc.setUpdatedTime(LocalDateTime.now());
            return false;
        }
        return true;
    }

    @Override
    public List<CartsItemVO>  getCartsList(int userId) {
        List<ShoppingCart> shoppingCartList = cartMapper.findAll(userId);
        List<CartsItemVO> cartsItemVO= new ArrayList<>();
        for(ShoppingCart sc : shoppingCartList){
            CartsItemVO cv = CartsItemVO.builder()
                    .id(sc.getProductId())
                    .imgUrl(sc.getImageUrl())
                    .name(sc.getName())
                    .price(sc.getPrice())
                    .quantity(sc.getQuantity())
                    .build();
            cartsItemVO.add(cv);
        }
        return cartsItemVO;
    }



    @Override
    public void deleteCartItem(int userId, int productId) {

        //查询数量

        //点击+-时的逻辑
//        ShoppingCart sc = cartMapper.findCartById(userId, productId);
//        ShoppingCart newsc = new ShoppingCart();
//        if(!(sc.getQuantity() == 0)){
//            return;
//        }else if(sc.getQuantity() > 1){
//            int i = sc.getQuantity();
//            i--;
//            BeanUtils.copyProperties(sc,newsc);
//            newsc.setQuantity(i);
//            cartMapper.updateCartById(newsc);
//        }
        //只有一件直接删除

        if (userId<=0 || productId<=0) {
            throw new IllegalStateException("userId和productId不能为空");
        }
        cartMapper.delete(userId,productId);
    }

    @Override
    public boolean update(int userId, int productId, int quantity) {
        ShoppingCart sc = ShoppingCart.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .build();
        if(!cartMapper.updateCartById(sc)){
            return false;
        }
        return true;
    }

    @Override
    public boolean clearCart(Long userId) {
        if(cartMapper.clear(userId)<0){
            return false;
        }
        return true;
    }
}
