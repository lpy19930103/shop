package com.shop.cart.service;


import com.shop.pojo.Cart;

import java.util.List;

public interface CartService {
    void saveItemByCart(Long userId, Long itemId, Integer num);

    void upDateItemByCart(Long userId, Long itemId, Integer num);

    void deleteItemByCart(Long userId, Long itemId);

    List<Cart> queryCartByUserId(Long userId);
}
