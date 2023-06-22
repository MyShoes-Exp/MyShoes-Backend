package com.acme.myshoes.platform.authentication.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String country;
    private String phone;
    private List<RoleResource> roles;
}
