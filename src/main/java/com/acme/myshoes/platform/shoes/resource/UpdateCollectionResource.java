package com.acme.myshoes.platform.shoes.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCollectionResource {
    @NotNull
    @NotBlank
    @Size(max=20)
    String name;
}
