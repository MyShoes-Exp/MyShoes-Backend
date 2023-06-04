package com.acme.myshoes.platform.shoes.api.rest;

import com.acme.myshoes.platform.shoes.domain.service.ShoeService;
import com.acme.myshoes.platform.shoes.mapping.ShoeMapper;
import com.acme.myshoes.platform.shoes.resource.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/shoes", produces = "application/json")
public class ShoeController {
    private final ShoeService shoeService;
    private  final ShoeMapper mapper;

    public ShoeController(ShoeService shoeService, ShoeMapper mapper) {
        this.shoeService = shoeService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ShoeResource> getAllShoes(Pageable pageable) {
        return mapper.modelListPage(shoeService.getAll(), pageable);
    }

    @GetMapping("{shoeId}")
    public ShoeResource getShoeById(@PathVariable Long shoeId) {
        return mapper.toResource(shoeService.getById(shoeId));
    }

    @PostMapping
    public ShoeResource createShoe(@RequestBody CreateShoeResource resource) {
        return mapper.toResource(shoeService.create(mapper.toModel(resource), resource.getCollection()));
    }

    @PutMapping("{shoeId}")
    public ShoeResource updateShoe(@PathVariable Long shoeId, @RequestBody UpdateShoeResource resource) {
        return mapper.toResource(shoeService.update(shoeId, mapper.toModel(resource)));
    }

    @DeleteMapping("{shoeId}")
    public ResponseEntity<?> deleteShoe(@PathVariable Long shoeId) {
        return shoeService.delete(shoeId);
    }
}
