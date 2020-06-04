package com.example.statemachine.config;

import com.example.statemachine.bean.Order;
import com.example.statemachine.constant.OrderEvent;
import com.example.statemachine.constant.OrderStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * @author liuteng
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
        states.withStates().initial(OrderStatus.PAID).states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStatus.PAID).target(OrderStatus.SEND)
                .event(OrderEvent.READY_SEND)
                .and()
                .withExternal()
                .source(OrderStatus.SEND).target(OrderStatus.RECEIVE)
                .event(OrderEvent.CONFIRM_ACC)
                .and()
                .withExternal()
                .source(OrderStatus.RECEIVE).target(OrderStatus.RETURN)
                .event(OrderEvent.REFUND_SUC);
    }

    @Bean
    public StateMachinePersister<OrderStatus, OrderEvent, Order> persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatus, OrderEvent, Order>() {
            @Override
            public void write(StateMachineContext<OrderStatus, OrderEvent> context, Order order) {
                order.setStatus(context.getState().getCode());
            }

            @Override
            public StateMachineContext<OrderStatus, OrderEvent> read(Order order) {
                return new DefaultStateMachineContext<>(OrderStatus.getOrderStatus(order.getStatus()), null, null, null);
            }
        });
    }

}
