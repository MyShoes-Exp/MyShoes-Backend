package com.acme.myshoes.platform.shopping.mapping;


import com.acme.myshoes.platform.shared.mapping.EnhancedModelMapper;
import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;
import com.acme.myshoes.platform.shopping.resource.CreateShoppingCartResource;
import com.acme.myshoes.platform.shopping.resource.ShoppingCartResource;
import com.acme.myshoes.platform.shopping.resource.UpdateShoppingCartResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ShoppingCartMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ShoppingCartResource toResource(ShoppingCart model){
        return mapper.map(model, ShoppingCartResource.class);
    }

    public Page<ShoppingCartResource> modelListPage(List<ShoppingCart> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ShoppingCartResource.class), pageable, modelList.size());
    }

    public ShoppingCart toModel(CreateShoppingCartResource resource){
        return mapper.map(resource, ShoppingCart.class);
    }

    public ShoppingCart toModel(UpdateShoppingCartResource resource){
        return mapper.map(resource, ShoppingCart.class);
    }
}
