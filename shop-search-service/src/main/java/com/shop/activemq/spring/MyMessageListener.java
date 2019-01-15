package com.shop.activemq.spring;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private SearchService searchService;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                // 获取消息内容
                String msg = textMessage.getText();

                if (StringUtils.isNotBlank(msg)) {
                    // 解析消息
                    // 把json转为jsonNode
                    JsonNode jsonNode = MAPPER.readTree(msg);

                    // 获取操作符
                    String type = jsonNode.get("type").asText();

                    // 获取商品id
                    long itemId = jsonNode.get("itemId").asLong();

                    System.out.println("itemId = " + itemId);

                    // 更新索引库
                    this.searchService.saveItem(itemId);

                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
