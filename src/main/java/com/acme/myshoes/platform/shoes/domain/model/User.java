package com.acme.myshoes.platform.shoes.domain.model;

import com.acme.myshoes.platform.orders.domain.model.Order;
import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 240)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 60)
    @Column(unique = true)
    private String email;

    @Size(max = 240)
    private String address;




}