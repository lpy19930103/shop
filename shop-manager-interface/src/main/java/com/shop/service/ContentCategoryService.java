package com.shop.service;

import com.shop.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService extends BaseService<TbContentCategory> {
    public List<TbContentCategory> queryContentCategoryByParentId(Long parentId);

    public TbContentCategory addContentCategory(TbContentCategory contentCategory);

    public void deleteContentCategory(Long id,Long parentId);
}
