package com.acme.myshoes.platform.orders.mapping;
import com.acme.myshoes.platform.orders.domain.model.OrderItem;
import com.acme.myshoes.platform.orders.resource.CreateOrderItemResource;
import com.acme.myshoes.platform.orders.resource.OrderItemResource;
import com.acme.myshoes.platform.orders.resource.UpdateOrderItemResource;
import com.acme.myshoes.platform.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class OrderItemMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;
    public OrderItemResource toResource(OrderItem model){
        return mapper.map(model, OrderItemResource.class);
    }

    public Page<OrderItemResource> modelListPage(List<OrderItem> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, OrderItemResource.class), pageable, modelList.size());
    }

    public OrderItem toModel(CreateOrderItemResource resource){
        return mapper.map(resource, OrderItem.class);
    }

    public OrderItem toModel(UpdateOrderItemResource resource){
        return mapper.map(resource, OrderItem.class);
    }

}
