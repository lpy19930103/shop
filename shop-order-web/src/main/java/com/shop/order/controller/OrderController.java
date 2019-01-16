package com.shop.order.controller;

import com.shop.cart.service.CartService;
import com.shop.common.utils.CookieUtils;
import com.shop.pojo.Cart;
import com.shop.pojo.TbUser;
import com.shop.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    @Value(value = "USER_TOKEN")
    private String USER_TOKEN;
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createOrder(HttpServletRequest request, Model model) {
        TbUser user = (TbUser) request.getAttribute("user");
        List<Cart> cartList = this.cartService.queryCartByUserId(user.getId());
        model.addAttribute("cartList", cartList);
        return "order-cart";
    }
}
