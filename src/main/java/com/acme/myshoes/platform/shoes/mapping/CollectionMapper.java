package com.acme.myshoes.platform.shoes.mapping;

import com.acme.myshoes.platform.shared.mapping.EnhancedModelMapper;
import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.resource.CollectionResource;
import com.acme.myshoes.platform.shoes.resource.CreateCollectionResource;
import com.acme.myshoes.platform.shoes.resource.UpdateCollectionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CollectionMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public CollectionResource toResource(Collection model){
        return mapper.map(model, CollectionResource.class);
    }

    public Page<CollectionResource> modelListPage(List<Collection> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, CollectionResource.class), pageable, modelList.size());
    }

    public Collection toModel(CreateCollectionResource resource){
        return mapper.map(resource, Collection.class);
    }

    public Collection toModel(UpdateCollectionResource resource){
        return mapper.map(resource, Collection.class);
    }
}
