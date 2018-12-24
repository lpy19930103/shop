package com.shop.service.impl;


import com.shop.pojo.TbItemCat;
import com.shop.service.TbItemCatService;
import org.springframework.stereotype.Service;



@Service
public class TbItemCatServiceImpl extends BaseServiceImpl<TbItemCat> implements TbItemCatService {

  /*  @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> listItem(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return tbItemCatMapper.select(null);
    }*/
}
