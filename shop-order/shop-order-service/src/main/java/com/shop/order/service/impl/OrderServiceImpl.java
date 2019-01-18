package com.shop.order.service.impl;

import com.shop.mapper.OrderItemMapper;
import com.shop.mapper.OrderMapper;
import com.shop.mapper.OrderShippingMapper;
import com.shop.order.service.OrderService;
import com.shop.order.service.redis.RedisUtils;
import com.shop.pojo.TbOrder;
import com.shop.pojo.TbOrderItem;
import com.shop.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl extends BaseServiceImpl<TbOrder> implements OrderService {
    @Value(value = "ORDER_SHOP_ORDERID_INCR")
    private String ORDER_SHOP_ORDERID_INCR;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Override
    public String saveOrder(TbOrder order) {
        String orderId = "" + order.getUserId() + redisUtils.incr(ORDER_SHOP_ORDERID_INCR);
        order.setOrderId(orderId);
        order.setStatus(1);
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());
        orderMapper.insertSelective(order);

        TbOrderItem orderItems = order.getOrderItems();
        orderItems.setOrderId(orderId);
        orderItemMapper.insertSelective(orderItems);

        TbOrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(orderShipping.getCreated());
        orderShippingMapper.insertSelective(orderShipping);

        return orderId;
    }

    @Override
    public TbOrder queryOrderById(String id) {
        TbOrderItem tbOrderItem = new TbOrderItem();
        tbOrderItem.setOrderId(id);
        TbOrderItem tbOrderItem1 = orderItemMapper.selectOne(tbOrderItem);
        TbOrderShipping tbOrderShipping = new TbOrderShipping();
        tbOrderShipping.setOrderId(id);
        TbOrderShipping tbOrderShipping1 = orderShippingMapper.selectOne(tbOrderShipping);
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(id);
        TbOrder tbOrder1 = orderMapper.selectOne(tbOrder);
        tbOrder.setOrderShipping(tbOrderShipping1);
        tbOrder.setOrderItems(tbOrderItem1);

        return tbOrder1;
    }
}
