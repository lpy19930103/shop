package com.shop.controller;

import com.shop.common.pojo.EasyResult;
import com.shop.pojo.TbContent;
import com.shop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("content")
public class ContentController {
    @Autowired
    private ContentService mContentService;


    @RequestMapping(value = "query/list", method = RequestMethod.GET)
    @ResponseBody
    public List<TbContent> queryContentByCategoryId(@RequestParam("categoryId") Long categoryId, @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "rows", defaultValue = "20") Integer rows) {
        return mContentService.queryContentByCategoryId(categoryId, page, rows);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public EasyResult queryContentByCategoryId(@RequestParam("ids") List<Long> ids) {
        mContentService.deleteByIds(ids);
        return new EasyResult<>(200);
    }


}
