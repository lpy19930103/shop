package com.shop.service.impl;

import com.shop.pojo.TbContent;
import com.shop.pojo.TbContentCategory;
import com.shop.service.ContentCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<TbContentCategory> implements ContentCategoryService {
    @Override
    public List<TbContentCategory> queryContentCategoryByParentId(Long parentId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        return super.queryListByWhere(tbContentCategory);
    }

    @Override
    public TbContentCategory addContentCategory(TbContentCategory contentCategory) {
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        TbContentCategory parent = queryById(contentCategory.getParentId());
        if (!parent.getIsParent()) {
            contentCategory.setIsParent(true);
            updateByIdSelective(parent);
        }
        save(contentCategory);
        return contentCategory;
    }

    @Override
    public void deleteContentCategory(Long id, Long parentId) {
        System.out.println("id = " + id + " parentId = " + parentId);
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(id);
        TbContentCategory tbContentCategory = queryById(id);
        if (tbContentCategory.getIsParent()) {
            TbContentCategory tbContentCategory1 = new TbContentCategory();
            tbContentCategory.setParentId(parentId);
            List<TbContentCategory> tbContentCategories = queryListByWhere(tbContentCategory1);
            for (TbContentCategory item : tbContentCategories) {
                if (!item.getIsParent()) {
                    ids.add(item.getId());
                }
            }
        }
        deleteByIds(ids);

    }
}
