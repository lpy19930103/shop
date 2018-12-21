package com.shop.service.impl;


import com.shop.mapper.TbItemCatMapper;
import com.shop.pojo.TbItemCat;
import com.shop.service.TbItemCatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Resource
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> listItem() {
        return tbItemCatMapper.listItem();
    }
}
