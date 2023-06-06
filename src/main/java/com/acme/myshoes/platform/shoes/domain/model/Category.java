package com.acme.myshoes.platform.shoes.domain.model;

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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Relationships
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoe")
    private Set<Shoe> shoes = new HashSet<>();
}
