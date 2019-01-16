package com.shop.cart.controller;

import com.shop.cart.service.CartService;
import com.shop.common.utils.CookieUtils;
import com.shop.pojo.Cart;
import com.shop.pojo.TbItemCat;
import com.shop.pojo.TbUser;
import com.shop.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Value(value = "USER_TOKEN")
    private String USER_TOKEN;

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String toCart() {
        return "cart";
    }


    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public String saveItemByCart(@PathVariable("itemId") Long itemId, Integer num, HttpServletRequest request) {

        String cookieValue = CookieUtils.getCookieValue(request, USER_TOKEN);
        cookieValue = "422";
        TbUser user = userService.queryUserByToken(cookieValue);
        if (user != null) {
            //已登录
            cartService.saveItemByCart(user.getId(), itemId, num);
        } else {
            // 用户未登录
        }

        // 重定向到购物车详情页
        return "redirect:/show.html";
    }

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String showCart(Model model, HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request, USER_TOKEN);
        cookieValue = "422";
        TbUser user = userService.queryUserByToken(cookieValue);
        List<Cart> cartList = null;
        // 判断用户是否登录
        if (user != null) {
            // 用户已登录
            cartList = this.cartService.queryCartByUserId(user.getId());

        } else {
            // 用户未登录

        }

        // 保存购物车到模型中
        model.addAttribute("cartList", cartList);

        return "cart";
    }


}
