package com.acme.myshoes.platform.orders.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderItemResource {
    private Long id;
    @NotNull
    @NotBlank
    private Long id_shoes;
}
