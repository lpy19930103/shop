package com.shop.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping(value = "{param}", method = RequestMethod.GET)
    public String toPage(Model model, @PathVariable(value = "param") String param, @RequestParam(value = "redirectURL", defaultValue = "") String redirectURL) {
        System.out.println(param);
        model.addAttribute("redirectURL", redirectURL);
        return param;
    }

}
