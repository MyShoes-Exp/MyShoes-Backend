package com.acme.myshoes.platform.shoes.domain.service;

import com.acme.myshoes.platform.shoes.domain.model.Category;
import com.acme.myshoes.platform.shoes.domain.model.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAll();
    Page<Category> getAll(Pageable pageable);
    Category getById(Long categoryId);
    Category getByName(String name);
    Category create(Category category);
    Category update(Long id, Category category);
    ResponseEntity<?> delete(Long categoryId); // este es un response porque al eliminar solo te devolvera si fue exitoso o no
}
