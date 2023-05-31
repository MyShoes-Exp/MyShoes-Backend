package com.acme.myshoes.platform.shoes.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

public class CreateCollectionResource {
    @Getter
    @Setter
    @With
    @NoArgsConstructor
    @AllArgsConstructor
    public class CollectionResource {
        @NotNull
        @NotBlank
        @Size(max=20)
        String name;
    }

}
