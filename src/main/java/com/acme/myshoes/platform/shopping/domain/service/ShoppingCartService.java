package com.acme.myshoes.platform.shopping.domain.service;

import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getAll();
    Page<ShoppingCart> getAll(Pageable pageable);
    ShoppingCart create(ShoppingCart shoppingCart);
    ResponseEntity<?> delete(Long shoppingCartId);
    void AddShoesToCart(Long shoppingCartId, Shoe shoe);
    ShoppingCart getById(Long shoppingcartId);

    ShoppingCart update(Long id, ShoppingCart shoppingCart);
}
