package com.shop.service.impl;

import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import com.shop.service.ItemDescService;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends BaseServiceImpl<TbItem> implements ItemService {

    @Autowired
    private ItemDescService itemDescService;

    @Override
    public void saveItem(TbItem tbItem, String desc) {
        System.out.println(tbItem.toString());
        tbItem.setStatus("1");
        super.save(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        itemDescService.save(tbItemDesc);

    }
}
