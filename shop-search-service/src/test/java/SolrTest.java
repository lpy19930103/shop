import com.github.pagehelper.PageHelper;
import com.shop.mapper.ItemMapper;
import com.shop.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value = "classpath*:spring/applicationContext-*.xml")
public class SolrTest {
/*
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private HttpSolrServer httpSolrServer;


    @Test
    public void creatAndUpdate() throws IOException, SolrServerException {
        // 可以使用循环来获取所有的商品数据
        int i = 1, pagesize = 500;
        do {
            // 分页查询商品数据
            PageHelper.startPage(i, 500);
            List<TbItem> list = itemMapper.select(null);

            // 遍历结果，把商品放到索引库中
            List<SolrInputDocument> docs = new ArrayList<>();
            for (TbItem item : list) {
                // 把获取的商品数据，批量导入到solr索引库中
                SolrInputDocument document = new SolrInputDocument();
                // 商品id
                document.setField("id", item.getId().toString());
                // 商品名称
                document.setField("item_title", item.getTitle());
                // 商品价格
                document.setField("item_price", item.getPrice());
                // 商品图片
                if (StringUtils.isNotBlank(item.getImage())) {
                    document.setField("item_image", item.getImage());
                } else {
                    document.setField("item_image", "");
                }
                // 商品类目id
                document.setField("item_cid", item.getCategory_id());
                // 商品状态
                document.setField("item_status", item.getStatus());

                // 把Document放到集合中，统一提交
                docs.add(document);
                System.out.println(item.toString());
            }

            // 把数据保存在solr索引库中
            this.httpSolrServer.add(docs);
            this.httpSolrServer.commit();

            i++;
            pagesize = list.size();

        } while (pagesize == 500);

  *//*      SolrInputDocument solrInputFields = new SolrInputDocument();
        solrInputFields.addField("id", "8847");
        solrInputFields.addField("title", "new2");
        //添加到索引库中
        httpSolrServer.add(solrInputFields);
        httpSolrServer.commit();*//*
    }


    @Test
    public void deleteIndex() throws IOException, SolrServerException {
        // 根据id删除索引数据
        this.httpSolrServer.deleteById("8849");
        // 根据条件删除（如果是*:*就表示全部删除，慎用）
//        this.httpSolrServer.deleteByQuery("*:*");
        this.httpSolrServer.commit();
    }

    @Test
    public void searchIndex() throws SolrServerException {
        // 创建搜索对象
        SolrQuery solrQuery = new SolrQuery();
        // 设置查询条件
        solrQuery.setQuery("title:new2");
        // 设置分页
        solrQuery.setStart(0);
        solrQuery.setRows(10);
        // 设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<font color=\"red\">");
        solrQuery.setHighlightSimplePost("</font>");
        // 查询数据
        QueryResponse query = this.httpSolrServer.query(solrQuery);
        SolrDocumentList results = query.getResults();
        System.out.println("搜索到的数据总条数：" + results.getNumFound());
        Map<String, Map<String, List<String>>> map = query.getHighlighting();
        // 解析查询结果
        for (SolrDocument solrDocument : results) {
            System.out.println("----------------------------------------------------");
            // 获取高亮数据
            List<String> list = map.get(solrDocument.get("id")).get("title");
            System.out.println("商品ID：" + solrDocument.get("id"));
            // 显示高亮
            if (list != null && list.size() > 0) {
                System.out.println("商品名称：" + list.get(0));
            } else {
                System.out.println("商品名称：" + solrDocument.get("title"));
            }
        }
    }*/
}
