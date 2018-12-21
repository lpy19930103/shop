package com.shop.controller;

import com.shop.pojo.TbItemCat;
import com.shop.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("itemCat")
public class TbItemCatController {


    @Autowired
    private TbItemCatService itemCatService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<TbItemCat> list() {
        return itemCatService.listItem();
    }

}
