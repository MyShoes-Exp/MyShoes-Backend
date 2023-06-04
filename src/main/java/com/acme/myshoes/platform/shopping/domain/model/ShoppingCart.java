package com.acme.myshoes.platform.shopping.domain.model;


import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@With
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long total;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shoppingCart")
    private List<Shoe> shoes = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="shopping_cart_id", nullable = false)
    @JsonIgnore
    private Collection collection;//reemplazar por User

    public ShoppingCart(){
        shoes = new ArrayList<>();
    }
    public void addShoe(Shoe shoe){
        shoes.add(shoe);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Shoe> getShoes() {
        return shoes;
    }

    public void setShoes(List<Shoe> shoes) {
        this.shoes = shoes;
    }

/*    public void getTotal(){
        for(Shoe shoe : shoes){
            total += shoe.getPrice();
        }
    }*/
}
