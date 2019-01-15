package com.shop.item.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import com.shop.service.ItemDescService;
import com.shop.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ItemMessageListener implements MessageListener {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String json = textMessage.getText();
                if (StringUtils.isNotBlank(json)) {
                    JsonNode jsonNode = MAPPER.readTree(json);
                    String type = jsonNode.get("type").asText();

                    long itemId = jsonNode.get("itemId").asLong();
                    System.out.println("itemId = " + itemId);
                    // 根据商品id生成静态化页面
                    genHtml(itemId);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDescService itemDescService;


    private void genHtml(long itemId) throws IOException, TemplateException {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");

        Map<String, Object> root = new HashMap<>();
        root.put("item", itemService.queryById(itemId));
        root.put("itemDesc", itemDescService.queryById(itemId));

        Writer out = new FileWriter(new File("c:/result/" + itemId + ".html"));
        template.process(root, out);
    }
}
