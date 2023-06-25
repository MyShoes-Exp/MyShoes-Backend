package com.acme.myshoes.platform.shoes.service;

import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.acme.myshoes.platform.shoes.domain.persistence.CategoryRepository;
import com.acme.myshoes.platform.shoes.domain.persistence.CollectionRepository;
import com.acme.myshoes.platform.shoes.domain.persistence.ShoeRepository;
import com.acme.myshoes.platform.shoes.domain.service.ShoeService;
import com.acme.myshoes.platform.shared.exception.ResourceNotFoundException;
import com.acme.myshoes.platform.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoeServiceImpl implements ShoeService {
    private static final String Entity = "Collection";
    private final ShoeRepository shoeRepository;
    private final CollectionRepository collectionRepository;
    private final CategoryRepository categoryRepository;
    private final Validator validator;

    public ShoeServiceImpl(ShoeRepository shoeRepository, CollectionRepository collectionRepository, CategoryRepository categoryRepository, Validator validator) {
        this.shoeRepository = shoeRepository;
        this.collectionRepository = collectionRepository;
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }

    @Override
    public List<Shoe> getAll() {
        return shoeRepository.findAll();
    }

    @Override
    public Page<Shoe> getAll(Pageable pageable) {
        return shoeRepository.findAll(pageable);
    }

    @Override
    public Shoe getById(Long shoeId) {
        return shoeRepository.findById(shoeId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, shoeId));
    }

    @Override
    public List<Shoe> getByCollectionId(Long collectionId) {
        //Revisar si existe
        Optional<Collection> collectionWithId = collectionRepository.findById(collectionId);
        if(!collectionWithId.isPresent())
            throw new ResourceNotFoundException(ENTITY, collectionId);
        return shoeRepository.findByCollectionId(collectionId);
    }

    @Override
    public List<Shoe> getByCollectionName(String name) {
        //Revisar si existe
        Optional<Collection> collectionWithName = collectionRepository.findByName(name);
        if(!collectionWithName.isPresent())
            throw new ResourceNotFoundException("No exists a collection with this name");
        return shoeRepository.findByCollectionName(name);
    }

    @Override
    public Shoe create(Shoe shoe,Long collectionId, Long categoryId) {
        shoe.setCollection(
                collectionRepository.findById(collectionId).orElseThrow(()-> new ResourceNotFoundException("No exists a collection with this id"))
        );
        shoe.setCategory(
                categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("No exists a collection with this id"))
        );
        Set<ConstraintViolation<Shoe>> violations = validator.validate(shoe);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if(shoeRepository.findByName(shoe.getName()).isPresent())
            throw new ResourceValidationException(ENTITY, "An collection with the same name already exists.");

        return shoeRepository.save(shoe);
    }

    @Override
    public Shoe update(Long id, Shoe shoe) {
        Set<ConstraintViolation<Shoe>> violations = validator.validate(shoe);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<Shoe> shoeWithName = shoeRepository.findByName(shoe.getName());

        if(shoeWithName.isPresent() && !shoeWithName.get().getId().equals(id))
            throw new ResourceValidationException(ENTITY, "An shoe with the same name already exists.");

        return shoeRepository.findById(id).map(existingShoe ->
                        shoeRepository.save(existingShoe.withName(shoe.getName())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long shoeId) {
        return shoeRepository.findById(shoeId).map(existingShoe -> {
            shoeRepository.delete(existingShoe);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, shoeId));
    }
}
