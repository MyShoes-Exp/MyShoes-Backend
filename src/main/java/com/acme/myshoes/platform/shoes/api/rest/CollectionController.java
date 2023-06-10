package com.acme.myshoes.platform.shoes.api.rest;
import com.acme.myshoes.platform.shoes.domain.service.CollectionService;
import com.acme.myshoes.platform.shoes.mapping.CollectionMapper;
import com.acme.myshoes.platform.shoes.resource.CollectionResource;
import com.acme.myshoes.platform.shoes.resource.CreateCollectionResource;
import com.acme.myshoes.platform.shoes.resource.UpdateCollectionResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/collections", produces = "application/json")
public class CollectionController {
    private final CollectionService collectionService;
    private  final CollectionMapper mapper;

    public CollectionController(CollectionService collectionService, CollectionMapper mapper) {
        this.collectionService = collectionService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<CollectionResource> getAllCollections(Pageable pageable) {
        return mapper.modelListPage(collectionService.getAll(), pageable);
    }

    @GetMapping("{collectionId}")
    public CollectionResource getCollectionById(@PathVariable Long collectionId) {
        return mapper.toResource(collectionService.getById(collectionId));
    }

    @PostMapping
    public CollectionResource createCollection(@RequestBody CreateCollectionResource resource) {
        return mapper.toResource(collectionService.create(mapper.toModel(resource)));
    }

    @PutMapping("{collectionId}")
    public CollectionResource updateCollection(@PathVariable Long collectionId, @RequestBody UpdateCollectionResource resource) {
        return mapper.toResource(collectionService.update(collectionId, mapper.toModel(resource)));
    }

    @DeleteMapping("{collectionId}")
    public ResponseEntity<?> deleteCollection(@PathVariable Long collectionId) {
        return collectionService.delete(collectionId);
    }
}