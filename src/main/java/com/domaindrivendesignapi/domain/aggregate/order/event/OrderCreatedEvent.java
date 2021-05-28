package com.domaindrivendesignapi.domain.aggregate.order.event;

import com.domaindrivendesignapi.domain.aggregate.order.Order;

public class OrderCreatedEvent {
    private Order order;

    public OrderCreatedEvent(Order order){
        this.order = order;
    }
}
