package com.acme.myshoes.platform.orders.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class OrderResource {

    private Long id;
    private String type_shoes;
}
