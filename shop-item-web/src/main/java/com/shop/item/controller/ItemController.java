package com.shop.item.controller;

import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import com.shop.service.ItemDescService;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDescService itemDescService;

    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public String queryById(Model model, @PathVariable(value = "itemId") Long itemId) {
        TbItem tbItem = itemService.queryById(itemId);
        TbItemDesc tbItemDesc = itemDescService.queryById(itemId);
        model.addAttribute("item", tbItem);
        model.addAttribute("itemDesc", tbItemDesc);
        return "item";
    }
}
