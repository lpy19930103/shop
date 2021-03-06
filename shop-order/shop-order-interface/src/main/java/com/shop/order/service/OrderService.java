package com.shop.order.service;

import com.shop.common.base.BaseService;
import com.shop.pojo.TbOrder;

public interface OrderService extends BaseService<TbOrder> {
    String saveOrder(TbOrder order);

    TbOrder queryOrderById(String id);

    void clearOrder();
}
