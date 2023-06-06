package com.acme.myshoes.platform.shoes.domain.model;

import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity //para la creación de su mapeo en base de datos
@Table(name = "shoe") // especificar el nombre para seguir la convencion correcta
public class Shoe extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=60)
    @Column(unique = true)
    private String name;

    private int size;

    //Relationships
    @ManyToOne
    @JoinColumn(name="collection_id", nullable = false)
    @JsonIgnore
    private Collection collection;
    
    @ManyToMany
    @JoinTable(name = "shopping_cart_shoe",
            joinColumns = @JoinColumn(name = "shoe_id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_cart_id"))
    @JsonIgnore
    private Set<ShoppingCart> shoppingCarts = new HashSet<>();
}
