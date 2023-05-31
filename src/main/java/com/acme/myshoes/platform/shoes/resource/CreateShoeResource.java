package com.acme.myshoes.platform.shoes.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateShoeResource {
    @NotNull
    @NotBlank
    @Size(max=20)
    String name;

    @NotNull
    @NotBlank
    @Size(max=20)
    Float size;

}
