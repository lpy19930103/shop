package com.shop.service.impl;


import com.github.pagehelper.PageHelper;
import com.shop.mapper.CategoryDao;
import com.shop.mapper.TbItemCatMapper;
import com.shop.pojo.TbItemCat;
import com.shop.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

//    @Autowired
//    private CategoryDao categoryDao;

    @Override
    public List<TbItemCat> listItem() {
        PageHelper.startPage(1,5);
//        categoryDao.select(null);
        return tbItemCatMapper.listItem();
    }
}
