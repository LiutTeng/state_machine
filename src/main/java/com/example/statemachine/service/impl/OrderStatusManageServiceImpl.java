package com.example.statemachine.service.impl;

import com.example.statemachine.bean.Order;
import com.example.statemachine.constant.OrderEvent;
import com.example.statemachine.constant.OrderStatus;
import com.example.statemachine.service.OrderStatusManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuteng
 */
@Service
public class OrderStatusManageServiceImpl implements OrderStatusManageService {

    @Resource
    private StateMachine<OrderStatus, OrderEvent> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderStatus, OrderEvent, Order> persister;

    @Override
    public boolean modifyOrderStatus(OrderEvent event, Order order) {

        boolean result = false;
        try {
            orderStateMachine.start();
            persister.restore(orderStateMachine, order);
            Message<OrderEvent> message = MessageBuilder.withPayload(event).setHeader("order", order).build();
            result = orderStateMachine.sendEvent(message);
            result = orderStateMachine.sendEvent(event);
            persister.persist(orderStateMachine, order);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
