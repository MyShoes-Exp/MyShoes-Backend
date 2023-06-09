package com.acme.myshoes.platform.shoes.mapping;

import com.acme.myshoes.platform.shared.mapping.EnhancedModelMapper;
import com.acme.myshoes.platform.shoes.domain.model.Category;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.acme.myshoes.platform.shoes.resource.CategoryResource;
import com.acme.myshoes.platform.shoes.resource.ShoeResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class CommentMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public CategoryResource toResource(Category model){
        return mapper.map(model, CategoryResource.class);
    }


}
