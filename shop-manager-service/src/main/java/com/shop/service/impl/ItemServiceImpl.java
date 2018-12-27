package com.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.pojo.EasyResult;
import com.shop.mapper.ItemMapper;
import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import com.shop.service.ItemDescService;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl extends BaseServiceImpl<TbItem> implements ItemService {

    @Autowired
    private ItemDescService itemDescService;
    @Autowired
    private ItemMapper itemMapper;

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

    @Override
    public EasyResult<TbItem> queryItemList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TbItem tbItem = new TbItem();
        tbItem.setStatus("1");
        List<TbItem> tbItems = queryListByWhere(tbItem);
        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItems);
        EasyResult<TbItem> tbItemEasyUIDataGridResult = new EasyResult<TbItem>();
        tbItemEasyUIDataGridResult.setRows(tbItems);
        tbItemEasyUIDataGridResult.setTotal(tbItemPageInfo.getTotal());
        return tbItemEasyUIDataGridResult;
    }
}
