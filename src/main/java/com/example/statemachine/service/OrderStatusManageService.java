package com.example.statemachine.service;

import com.example.statemachine.bean.Order;
import com.example.statemachine.constant.OrderEvent;

/**
 * @author liuteng
 */
public interface OrderStatusManageService {

    boolean modifyOrderStatus(OrderEvent event, Order order);

}
