package com.shop.controller;

import com.shop.common.pojo.EasyResult;
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


    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public EasyResult<TbItem> queryItemList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        return itemService.queryItemList(page, rows);
    }
}
