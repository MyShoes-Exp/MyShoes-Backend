package com.acme.myshoes.platform.shopping.domain.model;


import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shopping_cart")
    private Set<Shoe> shoes = new HashSet<>();
    //Se aplica para la rama Shoe tmb

    @OneToOne
    @JoinColumn(name="shopping_cart_id", nullable = false)
    @JsonIgnore
    private Collection collection;
    //cambiar private
}
