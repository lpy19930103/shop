package com.shop.service;

import java.util.List;
import com.shop.pojo.TbItemCat;
public interface TbItemCatService{

    int insert(TbItemCat tbItemCat);

    int insertSelective(TbItemCat tbItemCat);

    int insertList(List<TbItemCat> tbItemCats);

    int updateByPrimaryKeySelective(TbItemCat tbItemCat);
}
