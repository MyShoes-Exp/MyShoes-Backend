package com.acme.myshoes.platform.authentication.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserResource {
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
    private String email;

    @Size(max = 240)
    private String address;
}
