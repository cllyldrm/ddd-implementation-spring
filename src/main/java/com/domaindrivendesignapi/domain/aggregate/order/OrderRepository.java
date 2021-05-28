package com.domaindrivendesignapi.domain.aggregate.order;

public interface OrderRepository {
    long save(Order order);
    Order getById(long id);
}
