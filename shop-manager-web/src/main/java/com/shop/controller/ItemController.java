package com.shop.controller;

import com.shop.pojo.TbItem;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void saveItem(@RequestBody TbItem item) {
        itemService.saveItem(item, item.getDesc());
    }
}
