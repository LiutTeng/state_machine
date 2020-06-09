package com.example.statemachine.listener;

import com.example.statemachine.bean.Order;
import com.example.statemachine.constant.OrderEvent;
import com.example.statemachine.constant.OrderStatus;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author liuteng
 * 状态机监听器
 */
@Component
@WithStateMachine(name = "orderStateMachine")
public class OrderStatusListener {

    private static final String FORMAT = "事件: %s  单号: %s, 状态: %s";

    @OnTransition(source = "PAID", target = "SEND")
    public boolean sendTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        Optional.ofNullable(order).ifPresent(ord -> {
            order.setStatus(OrderStatus.SEND.getCode());
            System.out.println(String.format(FORMAT, message.getPayload().getEventCode(), ord.getOid(), ord.getStatus()));
            // TODO 持久化
        });
        return true;
    }

    @OnTransition(source = "SEND", target = "RECEIVE")
    public boolean receiveTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        Optional.ofNullable(order).ifPresent(ord -> {
            order.setStatus(OrderStatus.RECEIVE.getCode());
            System.out.println(String.format(FORMAT, message.getPayload().getEventCode(), ord.getOid(), ord.getStatus()));
            // TODO 持久化
        });
        return true;
    }

    @OnTransition(source = "RECEIVE", target = "RETURN")
    public boolean returnTransition(Message<OrderEvent> message){
        Order order = (Order) message.getHeaders().get("order");
        Optional.ofNullable(order).ifPresent(ord -> {
            order.setStatus(OrderStatus.RETURN.getCode());
            System.out.println(String.format(FORMAT, message.getPayload().getEventCode(), ord.getOid(), ord.getStatus()));
            // TODO 持久化
        });
        return true;
    }
}
