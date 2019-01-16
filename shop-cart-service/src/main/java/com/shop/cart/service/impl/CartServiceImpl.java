package com.shop.cart.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.cart.redis.RedisUtils;
import com.shop.cart.service.CartService;
import com.shop.mapper.CartMapper;
import com.shop.mapper.ItemMapper;
import com.shop.pojo.Cart;
import com.shop.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Value(value = "SHOP_CART_KEY")
    private String SHOP_CART_KEY;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ItemMapper itemMapper;

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void saveItemByCart(Long userId, Long itemId, Integer num) {
        List<Cart> carts = queryCartByUserId(userId);
        Cart cart = null;
        // 遍历购物车，商品是否存在
        for (Cart c : carts) {
            if (c.getItemId().longValue() == itemId.longValue()) {
                cart = c;
            }
        }
        // 判断商品在购物车是否存在
        if (cart != null) {
            // 如果存在,商品数量相加
            cart.setNum(cart.getNum() + num);
            cart.setUpdated(new Date());
        } else {
            cart = new Cart();
            TbItem item = itemMapper.selectByPrimaryKey(itemId);
            cart.setUserId(userId);
            cart.setItemId(itemId);
            cart.setItemTitle(item.getTitle());
            cart.setItemPrice(item.getPrice());
            cart.setItemImage(item.getImage());
            cart.setNum(num);
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            carts.add(cart);
        }
        // 把添加好的购物车保存在redis中
        try {
            redisUtils.set(SHOP_CART_KEY + userId, MAPPER.writeValueAsString(carts));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void upDateItemByCart(Long userId, Long itemId, Integer num) {
        List<Cart> carts = queryCartByUserId(userId);
        // 遍历购物车，商品是否存在
        for (Cart cart : carts) {
            if (cart.getItemId().longValue() == itemId.longValue()) {
                cart.setNum(num);
                cart.setUpdated(new Date());
            }
        }
        // 把添加好的购物车保存在redis中
        try {
            redisUtils.set(SHOP_CART_KEY + userId, MAPPER.writeValueAsString(carts));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItemByCart(Long userId, Long itemId) {
        List<Cart> carts = queryCartByUserId(userId);
        // 遍历购物车，商品是否存在
        for (Cart cart : carts) {
            if (cart.getItemId().longValue() == itemId.longValue()) {
                carts.remove(cart);
                try {
                    redisUtils.set(SHOP_CART_KEY + userId, MAPPER.writeValueAsString(carts));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public List<Cart> queryCartByUserId(Long userId) {
        // 从redis中查询购物车数据
        String json = redisUtils.get(SHOP_CART_KEY + userId);
        if (StringUtils.isNotBlank(json)) {
            try {
                return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<Cart>();
    }
}
