package com.shop.service;

import com.shop.pojo.TbItem;

public interface ItemService extends BaseService<TbItem> {
    public void saveItem(TbItem tbItem, String desc);
}
