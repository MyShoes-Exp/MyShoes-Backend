package com.acme.myshoes.platform.designing.api.rest;


import com.acme.myshoes.platform.designing.domain.service.DesignService;
import com.acme.myshoes.platform.designing.mapping.DesignMapper;
import com.acme.myshoes.platform.designing.resource.CreateDesignResource;
import com.acme.myshoes.platform.designing.resource.DesignResource;
import com.acme.myshoes.platform.designing.resource.UpdateDesignResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/design", produces = "application/json")
public class DesignController {
    private final DesignService designService;
    private final DesignMapper mapper;

    public DesignController(DesignService designService, DesignMapper mapper) {
        this.designService = designService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<DesignResource>getAllDesign(Pageable pageable){
        return mapper.modelListPage(designService.getAll(), pageable);
    }

    @GetMapping("{designId}")
    public DesignResource getDesignById(@PathVariable Long designId){
        return mapper.toResource(designService.getById(designId));
    }

    @PostMapping
    public DesignResource createDesign(@RequestBody CreateDesignResource resource){
        return mapper.toResource(designService.create(mapper.toModel(resource)));
    }

    @PutMapping("{designId}")
    public DesignResource updateDesign(@PathVariable Long designId, @RequestBody UpdateDesignResource resource){
        return mapper.toResource(designService.update(designId, mapper.toModel(resource)));
    }

    @DeleteMapping("{designId}")
    public ResponseEntity<?>deleteDesign(@PathVariable Long designId){
        return designService.delete(designId);
    }
}

