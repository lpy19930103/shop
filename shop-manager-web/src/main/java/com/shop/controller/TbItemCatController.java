package com.shop.controller;

import com.shop.pojo.TbItemCat;
import com.shop.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("item/cat")
public class TbItemCatController {


    @Autowired
    private TbItemCatService itemCatService;

    @RequestMapping(value = "query/{pageNum}", method = RequestMethod.GET)
    @ResponseBody
    public List<TbItemCat> list(@PathVariable("pageNum") Integer pageNum) {
        return itemCatService.queryByPage(pageNum, 10);
    }

    @RequestMapping()
    @ResponseBody
    public List<TbItemCat> queryItemCatByParentId(@RequestParam(value = "id",defaultValue = "0") Long parentId) {
        return itemCatService.queryItemCatByParentId(parentId);
    }

}
