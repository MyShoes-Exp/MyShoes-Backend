package com.acme.myshoes.platform.shoes.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResource {
    Long id;
    String name;
    Long user;
    Date createdAt;
    Date updatedAt;
}
