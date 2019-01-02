package com.shop.sso.controller;

import com.shop.pojo.TbUser;
import com.shop.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("list")
    @ResponseBody
    public List<TbUser> queryAll() {
        List<TbUser> tbUsers = userService.queryAll();
        return tbUsers;
    }

}
