package com.acme.myshoes.platform.authentication.domain.model;

import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 240)
    private String password;

    @NotNull
    @NotBlank
    private String country;

    @NotNull
    @NotBlank
    @Size(max = 9)
    private String  phone;

    @NotNull
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
}