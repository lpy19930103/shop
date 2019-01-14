package com.shop.search.service;

import com.shop.common.base.BaseService;
import com.shop.pojo.TbItem;

import java.util.List;

public interface SearchService extends BaseService<TbItem> {

    List<TbItem> searchAll();
}
