package com.domaindrivendesignapi.controller;

import com.domaindrivendesignapi.service.order.OrderService;
import com.domaindrivendesignapi.service.order.dto.OrderDto;
import com.domaindrivendesignapi.service.order.request.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody OrderRequest request) {
        long orderId = orderService.create(request);
        URI locationUri = URI.create("/orders/" + orderId);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable long id) {
        OrderDto response = orderService.get(id);
        return new ResponseEntity<>(response, OK);
    }
}
