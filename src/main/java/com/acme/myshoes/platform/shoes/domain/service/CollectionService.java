package com.acme.myshoes.platform.shoes.domain.service;

import com.acme.myshoes.platform.shoes.domain.model.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectionService {
    List<Collection> getAll();
    Page<Collection> getAll(Pageable pageable);
    Collection getById(Long collectionId);
    Collection create(Collection collection);
    Collection update(Long id, Collection collection);
    ResponseEntity<?> delete(Long collectionId); // este es un response porque al eliminar solo te devolvera si fue exitoso o no
}
