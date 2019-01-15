package com.shop.search.service;

import com.shop.common.base.BaseService;
import com.shop.common.pojo.EasyResult;
import com.shop.pojo.TbItem;


public interface SearchService extends BaseService<TbItem> {

    EasyResult<TbItem> search(String q, Integer page, Integer rows);

    void saveItem(Long itemId);
}
