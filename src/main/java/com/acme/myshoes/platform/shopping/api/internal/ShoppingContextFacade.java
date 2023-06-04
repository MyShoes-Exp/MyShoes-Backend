package com.acme.myshoes.platform.shopping.api.internal;

import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;

import java.util.List;

public interface ShoppingContextFacade {
    List<ShoppingCart>getAllShoppingCart();
}
