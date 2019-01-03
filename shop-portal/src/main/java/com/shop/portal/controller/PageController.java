package com.shop.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping(value = "{param}", method = RequestMethod.GET)
    public String toPage(@PathVariable(value = "param") String param) {
        System.out.println(param);
        return param;
    }
}
