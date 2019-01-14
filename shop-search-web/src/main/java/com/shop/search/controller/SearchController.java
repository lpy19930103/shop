package com.shop.search.controller;


import com.shop.common.pojo.EasyResult;
import com.shop.pojo.TbItem;
import com.shop.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model, @RequestParam("q") String q, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        EasyResult<TbItem> tbItems = searchService.search(q, page, 16);
        System.out.println(tbItems.getTotal());
        model.addAttribute("query", q);
        model.addAttribute("itemList", tbItems.getRows());
        model.addAttribute("totalPages", (tbItems.getTotal() + 15) / 16);
        model.addAttribute("page", page);
        return "search";
    }

}
