package com.domaindrivendesignapi.service.order;

import com.domaindrivendesignapi.domain.aggregate.common.DomainEventPublisher;
import com.domaindrivendesignapi.domain.aggregate.order.Order;
import com.domaindrivendesignapi.domain.aggregate.order.OrderRepository;
import com.domaindrivendesignapi.service.order.dto.OrderDto;
import com.domaindrivendesignapi.service.order.request.OrderRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final OrderAssembler orderAssembler;

    public OrderService(OrderRepository orderRepository, DomainEventPublisher domainEventPublisher, OrderAssembler orderAssembler) {
        this.orderRepository = orderRepository;
        this.domainEventPublisher = domainEventPublisher;
        this.orderAssembler = orderAssembler;
    }

    public long create(OrderRequest request) {
        Order order = new Order(null, request.getName());
        long orderId = orderRepository.save(order);
        order.setId(orderId);
        domainEventPublisher.publish(order);
        return orderId;
    }

    public OrderDto get(long id) {
        Order order = orderRepository.getById(id);

        if (order == null) {
            LOGGER.warn("Order couldn't be found with given order id: {}", id);
            return null;
        }

        return orderAssembler.toDto(order);
    }
}
