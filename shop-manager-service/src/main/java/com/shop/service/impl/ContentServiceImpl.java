package com.shop.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.pojo.TbContent;
import com.shop.service.ContentService;
import com.shop.service.redis.RedisPool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl extends BaseServiceImpl<TbContent> implements ContentService {
    private static final ObjectMapper MAPPER = new ObjectMapper();



    @Autowired
    private RedisPool poolJedisClient;

    @Value("${SHOP_PORTAL_AD}")
    private String SHOP_PORTAL_AD;

    @Override
    public List<TbContent> queryContentByCategoryId(Long id, Integer page, Integer rows) {
        TbContent tbContent = new TbContent();
        tbContent.setCategoryId(id);
        return queryByPage(tbContent, page, rows);
    }

    @Override
    public String queryAD() {
        String json = poolJedisClient.get(SHOP_PORTAL_AD);
        if (StringUtils.isNotEmpty(json)) {
            return json;
        }

        TbContent tbContent = new TbContent();
        tbContent.setCategoryId(17L);
        List<TbContent> tbContents = queryListByWhere(tbContent);
        List<Map<String, Object>> results = new ArrayList<>();
        for (TbContent item : tbContents) {
            Map<String, Object> map = new HashMap<>();
            map.put("srcB", item.getPic());
            map.put("height", 240);
            map.put("alt", "");
            map.put("width", 670);
            map.put("src", item.getPic());
            map.put("widthB", 550);
            map.put("href", item.getUrl());
            map.put("heightB", 240);
            results.add(map);
        }
        try {
            json = MAPPER.writeValueAsString(results);
            poolJedisClient.set(SHOP_PORTAL_AD, json, 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
