package com.ningling.service;


import com.ningling.DTO.CartAddDTO;
import com.ningling.DTO.CartUpdateQuantityDTO;
import com.ningling.Entity.ShoppingCart;
import com.ningling.VO.CartsItemVO;

import java.util.List;

public interface CartService {

    Boolean addToCart(CartAddDTO cartAddDTO);

    List<CartsItemVO> getCartsList(int userId);

    void deleteCartItem(int userId, int productId);

    boolean update(int userId, int productId, int quantity);

    boolean clearCart(Long userId);
}
