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
    public int insert(TbItemCat tbItemCat){
        return tbItemCatMapper.insert(tbItemCat);
    }

    @Override
    public int insertSelective(TbItemCat tbItemCat){
        return tbItemCatMapper.insertSelective(tbItemCat);
    }

    @Override
    public int insertList(List<TbItemCat> tbItemCats){
        return tbItemCatMapper.insertList(tbItemCats);
    }

    @Override
    public int updateByPrimaryKeySelective(TbItemCat tbItemCat){
        return tbItemCatMapper.updateByPrimaryKeySelective(tbItemCat);
    }
}
