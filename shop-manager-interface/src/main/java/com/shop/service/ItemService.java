package com.shop.service;

import com.shop.common.pojo.EasyUIDataGridResult;
import com.shop.pojo.TbItem;

public interface ItemService extends BaseService<TbItem> {
    public void saveItem(TbItem tbItem, String desc);

    public EasyUIDataGridResult<TbItem> queryItemList(Integer page, Integer rows);
}
