package com.domaindrivendesignapi.handler;

import com.domaindrivendesignapi.domain.aggregate.order.event.OrderCreatedEvent;
import com.domaindrivendesignapi.infrastructure.service.rabbitmq.RabbitMqService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.domaindrivendesignapi.handler.common.RabbitMqExchanges.ORDER_CREATED_EXCHANGE;

@Component
public class OrderCreatedEventHandler {
    private final RabbitMqService rabbitMqService;

    public OrderCreatedEventHandler(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @TransactionalEventListener
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        rabbitMqService.sendMessage(ORDER_CREATED_EXCHANGE, orderCreatedEvent);
    }
}
