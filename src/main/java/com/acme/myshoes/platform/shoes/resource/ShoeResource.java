package com.acme.myshoes.platform.shoes.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ShoeResource {
    Long id;
    String name;
    Float Size;
}
