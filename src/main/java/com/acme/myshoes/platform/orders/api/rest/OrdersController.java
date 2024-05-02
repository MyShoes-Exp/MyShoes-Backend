package com.acme.myshoes.platform.orders.api.rest;

import com.acme.myshoes.platform.orders.domain.service.OrderService;
import com.acme.myshoes.platform.orders.mapping.OrderMapper;
import com.acme.myshoes.platform.orders.resource.CreateOrderResource;
import com.acme.myshoes.platform.orders.resource.OrderResource;
import com.acme.myshoes.platform.orders.resource.UpdateOrderResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "api/v1/order", produces = "application/json")
public class OrdersController {

    private final OrderService orderService;
    private final OrderMapper mapper;


    public OrdersController(OrderService orderService, OrderMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<OrderResource> getAllOrder(Pageable pageable){
        return mapper.modelListPage(orderService.getAll(), pageable);
    }

    @GetMapping("{orderId}")
    public OrderResource getOrderById(@PathVariable Long orderId){
        return mapper.toResource(orderService.getById(orderId));
    }

    @PostMapping
    public OrderResource createOrder(@RequestBody CreateOrderResource resource){
        return mapper.toResource(orderService.create(mapper.toModel(resource)));
    }

    @PutMapping("{orderId}")
    public OrderResource updateOrder(@PathVariable Long orderId, @RequestBody UpdateOrderResource resource){
        return mapper.toResource(orderService.update(orderId, mapper.toModel(resource)));
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
        return orderService.delete(orderId);
    }

}
