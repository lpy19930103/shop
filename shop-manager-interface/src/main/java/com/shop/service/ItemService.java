package com.shop.service;

import com.shop.common.base.BaseService;
import com.shop.common.pojo.EasyResult;
import com.shop.pojo.TbItem;

public interface ItemService extends BaseService<TbItem> {
    public void saveItem(TbItem tbItem, String desc);

    public EasyResult<TbItem> queryItemList(Integer page, Integer rows);
}
