package com.acme.myshoes.platform.shopping.domain.persistence;

import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

}
