package com.acme.myshoes.platform.orders.service;


import com.acme.myshoes.platform.orders.domain.model.OrderItem;
import com.acme.myshoes.platform.orders.domain.persistence.OrderItemRepository;
import com.acme.myshoes.platform.orders.domain.service.OrderItemService;
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
public class OrderItemServiceImpl implements OrderItemService {


    private static final String ENTITY = "OrderItem";
    private final OrderItemRepository orderItemRepository;
    private final Validator validator;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, Validator validator) {
        this.orderItemRepository = orderItemRepository;
        this.validator = validator;
    }

    @Override
    public List<OrderItem> getAll(){
        return orderItemRepository.findAll();
    }

    @Override
    public Page<OrderItem> getAll(Pageable pageable){
        return orderItemRepository.findAll(pageable);
    }

    @Override
    public OrderItem create(OrderItem orderItem){
        Set<ConstraintViolation<OrderItem>> violations = validator.validate(orderItem);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return orderItemRepository.save(orderItem);
    }


    @Override
    public ResponseEntity<?> delete(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).map(order -> {
            orderItemRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, orderItemId));
    }


    public OrderItem getById(Long orderItemId){
        return orderItemRepository.findById(orderItemId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, orderItemId));
    }

    @Override
    public OrderItem update(Long id, OrderItem orderItem){
        Set<ConstraintViolation<OrderItem>>violations = validator.validate(orderItem);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Optional<OrderItem> orderItemWithId = orderItemRepository.findById(id);

        if(orderItemWithId.isPresent() && !orderItemWithId.get().getId().equals(id))
            throw new ResourceValidationException(ENTITY, "A order with id " + id + " does not exist");

        return orderItemRepository.findById(id).map(existingOrder ->
                        orderItemRepository.save(existingOrder.withId(orderItem.getId())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
