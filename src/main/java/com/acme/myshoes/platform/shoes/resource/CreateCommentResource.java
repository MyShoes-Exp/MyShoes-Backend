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
public class CreateCommentResource {
    @NotNull
    @NotBlank
    @Size(max=150)
    String name;
    Long shoe;
}
