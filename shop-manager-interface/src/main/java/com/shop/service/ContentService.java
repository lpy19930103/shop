package com.shop.service;


import com.shop.pojo.TbContent;

import java.util.List;

public interface ContentService extends BaseService<TbContent> {
    public List<TbContent> queryContentByCategoryId(Long id, Integer page, Integer rows);
}
