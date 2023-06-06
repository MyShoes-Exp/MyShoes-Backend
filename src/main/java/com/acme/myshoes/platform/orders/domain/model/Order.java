package com.acme.myshoes.platform.orders.domain.model;
import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.acme.myshoes.platform.shoes.domain.model.User;
import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@With
@Entity
@Table(name = "orders")
public class Order extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<User> user;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

}
