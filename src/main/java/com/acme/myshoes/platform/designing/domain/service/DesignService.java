package com.acme.myshoes.platform.designing.domain.service;

import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.acme.myshoes.platform.designing.domain.model.Design;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DesignService {
    List<Design> getAll();
    Page<Design> getAll(Pageable pageable);
    Design create(Design design);
    ResponseEntity<?> delete(Long designId);
    Design getById(Long design_id);

    Design update(Long id, Design design);
}
