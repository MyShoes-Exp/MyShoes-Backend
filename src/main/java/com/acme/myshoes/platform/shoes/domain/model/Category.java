package com.acme.myshoes.platform.shoes.domain.model;

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
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Size(max=60)
    @Column(unique = true)
    private String name;

    //Relationships
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Shoe> shoes = new HashSet<>();
}
