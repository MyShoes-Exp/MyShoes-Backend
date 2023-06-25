package com.acme.myshoes.platform.shoes.api.rest;

import com.acme.myshoes.platform.shoes.domain.service.CategoryService;
import com.acme.myshoes.platform.shoes.mapping.CategoryMapper;
import com.acme.myshoes.platform.shoes.resource.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/categories", produces = "application/json")
public class CategoryController {
    private final CategoryService categoryService;
    private  final CategoryMapper mapper;

    public CategoryController(CategoryService categoryService, CategoryMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<CategoryResource> getAllCategories(Pageable pageable) {
        return mapper.modelListPage(categoryService.getAll(), pageable);
    }
    @GetMapping("{categoryId}")
    public CategoryResource getCategoryById(@PathVariable Long categoryId) {
        return mapper.toResource(categoryService.getById(categoryId));
    }
    @PostMapping
    public CategoryResource createCategory(@RequestBody CreateCategoryResource resource) {
        return mapper.toResource(categoryService.create(mapper.toModel(resource)));
    }
    @PutMapping("{categoryId}")
    public CategoryResource updateCategory(@PathVariable Long categoryId, @RequestBody UpdateCategoryResource resource) {
        return mapper.toResource(categoryService.update(categoryId, mapper.toModel(resource)));
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.delete(categoryId);
    }

}
