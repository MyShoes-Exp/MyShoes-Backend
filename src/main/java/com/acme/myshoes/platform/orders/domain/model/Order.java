package com.acme.myshoes.platform.orders.domain.model;
import com.acme.myshoes.platform.shared.domain.model.AuditModel;
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
    private Set<ShoppingCart> Shopping;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<User> user;

    public Order addOrder(String userName) {
        if (user == null) {
            user = new HashSet<>();
        }

        // Add Criterion to Order
        user.add(new User()
                .withName(userName)
                .withOrder(this));

        return this;
    }

}
