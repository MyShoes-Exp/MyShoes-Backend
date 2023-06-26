package com.acme.myshoes.platform.shoes.resource;

import com.acme.myshoes.platform.shoes.domain.model.Category;
import com.acme.myshoes.platform.shoes.domain.model.Collection;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ShoeResource {
    Long id;
    String name;
    int Size;
    String img;
    int price;
    Long collection_id;
    Long category_id;
}
