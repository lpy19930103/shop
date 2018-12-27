package com.shop.controller;

import com.shop.common.pojo.EasyResult;
import com.shop.pojo.TbContentCategory;
import com.shop.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<TbContentCategory> queryContentCategoryByParentId(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return contentCategoryService.queryContentCategoryByParentId(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public EasyResult<TbContentCategory> addContentCategory(TbContentCategory contentCategory) {
        TbContentCategory tbContentCategory = contentCategoryService.addContentCategory(contentCategory);
        return new EasyResult<>(200, null, tbContentCategory);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public EasyResult updateContentCategory(TbContentCategory contentCategory) {
        contentCategoryService.updateByIdSelective(contentCategory);
        return new EasyResult<>(200);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public EasyResult deleteContentCategory(@RequestParam(value = "id") Long id, @RequestParam(value = "parentId") Long parentId) {
        contentCategoryService.deleteContentCategory(id, parentId);
        return new EasyResult<>(200);
    }

}
