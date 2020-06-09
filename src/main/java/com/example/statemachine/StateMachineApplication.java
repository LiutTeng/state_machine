package com.example.statemachine;

import com.example.statemachine.bean.Order;
import com.example.statemachine.constant.OrderEvent;
import com.example.statemachine.constant.OrderStatus;
import com.example.statemachine.service.OrderStatusManageService;
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

        OrderStatusManageService orderStatusManageService = context.getBean(OrderStatusManageService.class);
        Order order = new Order();
        order.setOid("1001200006");
        order.setStatus(OrderStatus.PAID.getCode());

        boolean result = orderStatusManageService.modifyOrderStatus(OrderEvent.READY_SEND, order);

        // TODO .....

    }

}
