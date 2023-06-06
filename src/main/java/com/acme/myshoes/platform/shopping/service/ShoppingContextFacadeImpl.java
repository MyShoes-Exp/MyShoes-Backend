package com.acme.myshoes.platform.shopping.service;

import com.acme.myshoes.platform.shopping.api.internal.ShoppingContextFacade;
import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;
import com.acme.myshoes.platform.shopping.domain.service.ShoppingCartService;

import java.util.List;

public class ShoppingContextFacadeImpl implements ShoppingContextFacade {
    private final ShoppingCartService shoppingCartService;
    public ShoppingContextFacadeImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartService.getAll();
    }
}
