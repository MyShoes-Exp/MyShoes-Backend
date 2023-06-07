package com.acme.myshoes.platform.orders.api.rest;

import com.acme.myshoes.platform.orders.domain.service.OrderItemService;
import com.acme.myshoes.platform.orders.mapping.OrderItemMapper;
import com.acme.myshoes.platform.orders.resource.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/orderItem", produces = "application/json")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderItemMapper mapper;

    public OrderItemController(OrderItemService orderItemService, OrderItemMapper mapper) {
        this.orderItemService = orderItemService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<OrderItemResource> getAllOrderItem(Pageable pageable){
        return mapper.modelListPage(orderItemService.getAll(), pageable);
    }

    @GetMapping("{orderItemId}")
    public OrderItemResource getOrderItemById(@PathVariable Long orderItemId){
        return mapper.toResource(orderItemService.getById(orderItemId));
    }

    @PostMapping
    public OrderItemResource createOrderItem(@RequestBody CreateOrderItemResource resource){
        return mapper.toResource(orderItemService.create(mapper.toModel(resource)));
    }

    @PutMapping("{orderItemId}")
    public OrderItemResource updateOrderItem(@PathVariable Long orderItemId, @RequestBody UpdateOrderItemResource resource){
        return mapper.toResource(orderItemService.update(orderItemId, mapper.toModel(resource)));
    }

    @DeleteMapping("{orderItemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long orderItemId){
        return orderItemService.delete(orderItemId);
    }



}
