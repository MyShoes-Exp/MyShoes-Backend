package com.acme.myshoes.platform.shoes.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CommentResource {
    Long id;
    String name;
    Long shoe_id;

}
