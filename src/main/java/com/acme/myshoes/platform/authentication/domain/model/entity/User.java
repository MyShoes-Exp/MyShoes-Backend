package com.acme.myshoes.platform.authentication.domain.model.entity;

import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Email
    @Column(unique = true)
    private String email;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(@NotNull String name, @NotNull String email, String password, String country, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.phone = phone;
    }
}