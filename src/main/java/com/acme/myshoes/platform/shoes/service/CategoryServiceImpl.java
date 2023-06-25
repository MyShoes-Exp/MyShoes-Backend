package com.acme.myshoes.platform.shoes.service;

import com.acme.myshoes.platform.shoes.domain.model.Category;
import com.acme.myshoes.platform.shoes.domain.persistence.CategoryRepository;
import com.acme.myshoes.platform.shoes.domain.service.CategoryService;
import com.acme.myshoes.platform.shoes.exception.ResourceNotFoundException;
import com.acme.myshoes.platform.shoes.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String Entity = "Category";
    private final CategoryRepository categoryRepository;
    private final Validator validator;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Validator validator) {
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY, categoryId));
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("No exists a category with this name"));
    }

    @Override
    public Category create(Category category) {
        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if(categoryRepository.findByName(category.getName()).isPresent())
            throw new ResourceValidationException(ENTITY, "An category with the same name already exists.");
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Optional<Category> categoryWithName = categoryRepository.findByName(category.getName());

        if(categoryWithName.isPresent() && !categoryWithName.get().getId().equals(id))
            throw new ResourceValidationException(ENTITY, "An category with the same name already exists.");

        return categoryRepository.findById(id).map(existingCategory ->
                        categoryRepository.save(existingCategory.withName(category.getName())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }
    @Override
    public ResponseEntity<?> delete(Long categoryId) {
        return categoryRepository.findById(categoryId).map(existingCategory -> {
            categoryRepository.delete(existingCategory);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, categoryId));
    }
}
