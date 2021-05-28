package com.domaindrivendesignapi.domain.aggregate.order;

import com.domaindrivendesignapi.domain.aggregate.common.AggregateRoot;
import com.domaindrivendesignapi.domain.aggregate.order.event.OrderCreatedEvent;
import lombok.Getter;

@Getter
public class Order extends AggregateRoot {
    private Long id;
    private String name;

    public Order(Long id, String name){
        if(id == null){
            registerEvent(new OrderCreatedEvent(this));
        }
        this.name = name;
    }

    public void setId(long id){
        if (this.id == null){
            this.id = id;
        }
    }
}
