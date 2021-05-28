package com.domaindrivendesignapi.service.order;

import com.domaindrivendesignapi.domain.aggregate.order.Order;
import com.domaindrivendesignapi.service.order.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderAssembler {
    public OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderDto.getId());
        orderDto.setName(orderDto.getName());
        return orderDto;
    }
}
