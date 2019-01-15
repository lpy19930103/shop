package com.shop.cart.service;


import com.shop.pojo.Cart;

import java.util.List;

public interface CartService {
    void saveItemByCart(Long userId, Long itemId, Integer num);

    List<Cart> queryCartByUserId(Long userId);
}
