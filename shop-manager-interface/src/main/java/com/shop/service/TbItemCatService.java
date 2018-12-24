package com.shop.service;

import java.util.List;

import com.shop.pojo.TbItemCat;

public interface TbItemCatService extends BaseService<TbItemCat> {
    List<TbItemCat> queryItemCatByParentId(Long id);
}
