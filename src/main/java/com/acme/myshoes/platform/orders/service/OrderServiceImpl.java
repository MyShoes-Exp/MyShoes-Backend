package com.acme.myshoes.platform.orders.service;

import com.acme.myshoes.platform.orders.domain.model.Order;
import com.acme.myshoes.platform.orders.domain.persistence.OrderRepository;
import com.acme.myshoes.platform.orders.domain.service.OrderService;
import com.acme.myshoes.platform.shoes.exception.ResourceNotFoundException;
import com.acme.myshoes.platform.shoes.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class OrderServiceImpl implements OrderService {
    private static final String ENTITY = "Order";
    private final OrderRepository orderRepository;
    private final Validator validator;

    public OrderServiceImpl(OrderRepository orderRepository, Validator validator) {
        this.orderRepository = orderRepository;
        this.validator = validator;
    }

    @Override
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getAll(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order create(Order order){
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return orderRepository.save(order);
    }


    @Override
    public ResponseEntity<?> delete(Long orderId) {
        return orderRepository.findById(orderId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, orderId));
    }


    public Order getById(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, orderId));
    }

    @Override
    public Order update(Long id, Order order){
        Set<ConstraintViolation<Order>>violations = validator.validate(order);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Optional<Order> orderWithId = orderRepository.findById(id);

        if(orderWithId.isPresent() && !orderWithId.get().getId().equals(id))
            throw new ResourceValidationException(ENTITY, "A shopping cart with id " + id + " does not exist");

        return orderRepository.findById(id).map(existingOrder ->
                        orderRepository.save(existingOrder.withId(order.getId())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

}
