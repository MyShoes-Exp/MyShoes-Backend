package com.acme.myshoes.platform.shoes.domain.model;

import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "collection")
public class Collection extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max=60)
    @Column(unique = true)
    private String name;

    @Column(name = "user_id")
    private Long user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "collection")
    private Set<Shoe> shoes = new HashSet<>();
}
