package com.acme.myshoes.platform.orders.resource;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResource {
    private Long id;
    @NotNull
    @NotBlank
    @Column
    private Long id_shoes;
}
