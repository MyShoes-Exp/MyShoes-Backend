package com.acme.myshoes.platform.shoes.mapping;

import com.acme.myshoes.platform.shared.mapping.EnhancedModelMapper;
import com.acme.myshoes.platform.shoes.domain.model.Category;
import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.acme.myshoes.platform.shoes.resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CategoryMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public CategoryResource toResource(Category model){
        return mapper.map(model, CategoryResource.class);
    }

    public Page<CategoryResource> modelListPage(List<Category> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, CategoryResource.class), pageable, modelList.size());
    }

    public List<CategoryResource> modelListResource(List<Category> modelList){
        return mapper.mapList(modelList, CategoryResource.class);
    }

    public Category toModel(CreateCollectionResource resource){
        return mapper.map(resource, Category.class);
    }

    public Category toModel(UpdateCategoryResource resource){
        return mapper.map(resource, Category.class);
    }
}
