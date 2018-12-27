package com.shop.service.impl;

import com.shop.pojo.TbContent;
import com.shop.service.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl extends BaseServiceImpl<TbContent> implements ContentService {

    @Override
    public List<TbContent> queryContentByCategoryId(Long id, Integer page, Integer rows) {
        TbContent tbContent = new TbContent();
        tbContent.setCategoryId(id);
        return queryByPage(tbContent, page, rows);
    }
}
