package com.shop.search.service.impl;

import com.shop.pojo.TbItem;
import com.shop.search.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class SearchServiceImpl extends BaseServiceImpl<TbItem> implements SearchService {
    @Autowired
    private Mapper<TbItem> itemMapper;

    @Override
    public List<TbItem> searchAll() {

        return itemMapper.select(null);
    }
}
