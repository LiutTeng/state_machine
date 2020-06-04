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
 */
@Component
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListener {

    @OnTransition(source = "PAID", target = "SEND")
    public boolean sendTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        Optional.ofNullable(order).ifPresent(ord->ord.setStatus(OrderStatus.SEND.getCode()));
        System.out.println("发货 headers=" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "SEND", target = "RECEIVE")
    public boolean receiveTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        Optional.ofNullable(order).ifPresent(ord->ord.setStatus(OrderStatus.RECEIVE.getCode()));
        System.out.println("收货 headers=" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "RECEIVE", target = "RETURN")
    public boolean returnTransition(Message<OrderEvent> message){
        Order order = (Order) message.getHeaders().get("order");
        Optional.ofNullable(order).ifPresent(ord->ord.setStatus(OrderStatus.RETURN.getCode()));
        System.out.println("退款 headers=" + message.getHeaders().toString());
        return true;
    }
}
