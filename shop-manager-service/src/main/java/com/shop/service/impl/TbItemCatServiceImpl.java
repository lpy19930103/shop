package com.shop.service.impl;


import com.shop.pojo.TbItemCat;
import com.shop.service.TbItemCatService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TbItemCatServiceImpl extends BaseServiceImpl<TbItemCat> implements TbItemCatService {
    @Override
    public List<TbItemCat> queryItemCatByParentId(Long id) {
        TbItemCat tbItemCat = new TbItemCat();
        tbItemCat.setParentId(id);
        return super.queryListByWhere(tbItemCat);
    }

  /*  @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> listItem(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return tbItemCatMapper.select(null);
    }*/
}
