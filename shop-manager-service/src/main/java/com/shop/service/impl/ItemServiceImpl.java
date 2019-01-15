package com.shop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.pojo.EasyResult;
import com.shop.mapper.ItemMapper;
import com.shop.pojo.TbItem;
import com.shop.pojo.TbItemDesc;
import com.shop.service.ItemDescService;
import com.shop.service.ItemService;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ItemServiceImpl extends BaseServiceImpl<TbItem> implements ItemService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private ItemDescService itemDescService;
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    Destination destination;

    @Override
    public void saveItem(TbItem tbItem, String desc) {
        System.out.println(tbItem.toString());
        tbItem.setStatus("1");
        super.save(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        itemDescService.save(tbItemDesc);
        sendMQ("save", tbItem.getId());
    }


    public void sendMQ(final String type, final Long itemId) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 声明消息
                TextMessage textMessage = new ActiveMQTextMessage();

                // 构建消息内容
                // 使用json格式的数据封装需要传递的消息
                Map<String, Object> map = new HashMap<>();
                // 操作符
                map.put("type", type);
                // 商品id
                map.put("itemId", itemId);

                try {
                    // 把map转为接送格式的数据
                    String json = MAPPER.writeValueAsString(map);
                    // 设置消息内容
                    textMessage.setText(json);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return textMessage;
            }
        });

    }

    @Override
    public EasyResult<TbItem> queryItemList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TbItem tbItem = new TbItem();
        tbItem.setStatus("1");
        List<TbItem> tbItems = queryListByWhere(tbItem);
        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItems);
        EasyResult<TbItem> tbItemEasyUIDataGridResult = new EasyResult<TbItem>();
        tbItemEasyUIDataGridResult.setRows(tbItems);
        tbItemEasyUIDataGridResult.setTotal(tbItemPageInfo.getTotal());
        return tbItemEasyUIDataGridResult;
    }
}
