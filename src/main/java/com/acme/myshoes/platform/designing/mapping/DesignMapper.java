package com.acme.myshoes.platform.designing.mapping;


import com.acme.myshoes.platform.shared.mapping.EnhancedModelMapper;
import com.acme.myshoes.platform.designing.domain.model.Design;
import com.acme.myshoes.platform.designing.resource.CreateDesignResource;
import com.acme.myshoes.platform.designing.resource.DesignResource;
import com.acme.myshoes.platform.designing.resource.UpdateDesignResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DesignMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public DesignResource toResource(Design model){
        return mapper.map(model, DesignResource.class);
    }

    public Page<DesignResource> modelListPage(List<Design> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, DesignResource.class), pageable, modelList.size());
    }

    public Design toModel(CreateDesignResource resource){
        return mapper.map(resource, Design.class);
    }

    public Design toModel(UpdateDesignResource resource){
        return mapper.map(resource, Design.class);
    }
}
