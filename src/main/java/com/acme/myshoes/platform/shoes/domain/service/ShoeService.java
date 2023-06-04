package com.acme.myshoes.platform.shoes.domain.service;

import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoeService {
    List<Shoe> getAll();
    Page<Shoe> getAll(Pageable pageable);
    Shoe getById(Long shoeId);
    List<Shoe> getByCollectionId(Long collectionId);
    List<Shoe> getByCollectionName(String name);
    Shoe create(Shoe shoe, Long collectionId);
    Shoe update(Long id, Shoe shoe);
    ResponseEntity<?> delete(Long shoeId); // este es un response porque al eliminar solo te devolvera si fue exitoso o no

}
