package com.shop.order.controller;

import com.shop.cart.service.CartService;
import com.shop.common.pojo.EasyResult;
import com.shop.common.utils.CookieUtils;
import com.shop.order.service.OrderService;
import com.shop.pojo.Cart;
import com.shop.pojo.TbOrder;
import com.shop.pojo.TbUser;
import com.shop.sso.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {
    @Value(value = "USER_TOKEN")
    private String USER_TOKEN;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createOrder(HttpServletRequest request, Model model) {
        TbUser user = (TbUser) request.getAttribute("user");
        List<Cart> cartList = this.cartService.queryCartByUserId(user.getId());
        model.addAttribute("cartList", cartList);
        return "order-cart";
    }

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    public EasyResult submitOrder(HttpServletRequest request, @RequestBody TbOrder order) {
        TbUser user = (TbUser) request.getAttribute("user");
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        String orderId = orderService.saveOrder(order);
        EasyResult<String> stringEasyResult = new EasyResult<>();
        stringEasyResult.setStatus(200);
        stringEasyResult.setDTO(orderId);

        return stringEasyResult;
    }

    @RequestMapping(value = "success")
    public String toSuccess(@RequestParam("id") String id, Model model) {
        System.out.println("id" + id);
        TbOrder tbOrder = orderService.queryOrderById(id);
        model.addAttribute("orderId", id);
        // 获取当前时间的两天后，即时送达时间
        String date = new DateTime().plusDays(2).toString("yyyy年MM月dd日HH时mm分ss秒SSS毫秒");
        model.addAttribute("date", date);
        model.addAttribute("payment", tbOrder.getPayment());
        return "success";
    }
}
