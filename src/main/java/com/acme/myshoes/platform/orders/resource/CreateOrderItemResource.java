package com.acme.myshoes.platform.orders.resource;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@NoArgsConstructor
@With
public class CreateOrderItemResource {

    @NotNull
    @NotBlank
    private Long id_shoes;
}
