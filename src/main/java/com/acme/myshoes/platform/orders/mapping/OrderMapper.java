package com.acme.myshoes.platform.orders.mapping;

import com.acme.myshoes.platform.orders.domain.model.Order;
import com.acme.myshoes.platform.orders.resource.CreateOrderResource;
import com.acme.myshoes.platform.orders.resource.OrderResource;
import com.acme.myshoes.platform.orders.resource.UpdateOrderResource;
import com.acme.myshoes.platform.shared.mapping.EnhancedModelMapper;
import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class OrderMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public OrderResource toResource(ShoppingCart model){
        return mapper.map(model, OrderResource.class);
    }

    public Page<OrderResource> modelListPage(List<Order> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, OrderResource.class), pageable, modelList.size());
    }

    public Order toModel(CreateOrderResource resource){
        return mapper.map(resource, Order.class);
    }

    public Order toModel(UpdateOrderResource resource){
        return mapper.map(resource, Order.class);
    }

}
