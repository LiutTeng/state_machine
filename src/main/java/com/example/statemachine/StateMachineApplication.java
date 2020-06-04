package com.example.statemachine;

import com.example.statemachine.bean.Order;
import com.example.statemachine.constant.OrderEvent;
import com.example.statemachine.constant.OrderStatus;
import com.example.statemachine.service.OrderStatusService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liuteng
 */
@SpringBootApplication
public class StateMachineApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StateMachineApplication.class, args);
        OrderStatusService orderStatusService = context.getBean(OrderStatusService.class);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.getCode());
        orderStatusService.sendMessage(OrderEvent.READY_SEND, order);

    }

}
