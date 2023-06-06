package com.acme.myshoes.platform.designing.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "design")
public class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long shoe_id;
    private String color;
    private String material;
}
