package com.shop.portal.controller;


import com.shop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(method = RequestMethod.GET)
    public String toIndex(Model model) {
        String json = contentService.queryAD();
        model.addAttribute("ad1", json);
        return "index";
    }
}
