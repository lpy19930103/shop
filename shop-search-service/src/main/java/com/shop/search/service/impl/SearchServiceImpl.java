package com.shop.search.service.impl;

import com.shop.common.pojo.EasyResult;
import com.shop.pojo.TbItem;
import com.shop.search.service.SearchService;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl extends BaseServiceImpl<TbItem> implements SearchService {
    @Autowired
    HttpSolrServer httpSolrServer;


    @Override
    public EasyResult<TbItem> search(String q, Integer page, Integer rows) {
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询语句
        if (StringUtils.isNotBlank(q)) {
            solrQuery.setQuery("item_title:" + q + " AND item_status:1");
        } else {
            solrQuery.setQuery("item_status:1");
        }
        // 设置分页
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);

        // 高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");

        EasyResult<TbItem> tbItemEasyResult = new EasyResult<>();

        try {
            QueryResponse query = httpSolrServer.query(solrQuery);
            SolrDocumentList results = query.getResults();
            Map<String, Map<String, List<String>>> map = query.getHighlighting();
            // 解析结果集
            // 声明存放商品的集合
            List<TbItem> list = new ArrayList<>();
            for (SolrDocument solrDocument : results) {
                TbItem item = new TbItem();

                // 解析Document
                // 商品id
                item.setId(Long.parseLong(solrDocument.get("id").toString()));

                // 获取高亮的数据
                List<String> hlist = map.get(solrDocument.get("id").toString()).get("item_title");

                // 商品title
                if (hlist != null && hlist.size() > 0) {
                    item.setTitle(hlist.get(0));
                } else {
                    item.setTitle(solrDocument.get("item_title").toString());
                }

                // 商品图片image
                item.setImage(solrDocument.get("item_image").toString());

                // 商品价格price
                item.setPrice(solrDocument.get("item_price").toString());

                // 商品cid
                item.setCategory_id(Long.parseLong(solrDocument.get("item_cid").toString()));

                // 把封装好的商品数据放到集合中
                list.add(item);

            }
            // 封装返回数据taoResult
            // 设置结果集
            tbItemEasyResult.setRows(list);
            // 设置查询的数据总条数
            tbItemEasyResult.setTotal(results.getNumFound());
            return tbItemEasyResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
