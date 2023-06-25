package com.acme.myshoes.platform.shoes.service;

import com.acme.myshoes.platform.authentication.api.internal.AuthenticationContextFacade;
import com.acme.myshoes.platform.authentication.domain.model.User;
import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.domain.persistence.CollectionRepository;
import com.acme.myshoes.platform.shoes.domain.service.CollectionService;
import com.acme.myshoes.platform.shared.exception.ResourceNotFoundException;
import com.acme.myshoes.platform.shared.exception.ResourceValidationException;
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
public class CollectionServiceImpl implements CollectionService {

    private static final String Entity = "Collection";
    private final CollectionRepository collectionRepository;
    private final Validator validator;
    private final AuthenticationContextFacade authenticationContextFacade;

    public CollectionServiceImpl(CollectionRepository collectionRepository, Validator validator, AuthenticationContextFacade authenticationContextFacade) {
        this.collectionRepository = collectionRepository;
        this.validator = validator;
        this.authenticationContextFacade = authenticationContextFacade;
    }

    @Override
    public List<Collection> getAll() {
        return collectionRepository.findAll();
    }

    @Override
    public Page<Collection> getAll(Pageable pageable) {
        return collectionRepository.findAll(pageable);
    }

    @Override
    public Collection getById(Long collectionId) {
        return collectionRepository.findById(collectionId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY, collectionId));
    }

    @Override
    public Collection getByName(String name) {
        return collectionRepository.findByName(name)
                .orElseThrow(()-> new ResourceNotFoundException("No exists a collection with this name"));
    }

    @Override
    public List<Collection> getByUser(Long user_id) {
        return collectionRepository.findAllByUser(user_id);
    }

    @Override
    public Collection create(Collection collection) {
        Set<ConstraintViolation<Collection>> violations = validator.validate(collection);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if(collectionRepository.findByName(collection.getName()).isPresent())
            throw new ResourceValidationException(ENTITY, "An collection with the same name already exists.");
        Optional<User> userWithId = authenticationContextFacade.getUserById(collection.getUser());
        if (!userWithId.isPresent())
            throw new ResourceValidationException(ENTITY, "A user with that ID doesn't exist.");
        return collectionRepository.save(collection);
    }

    @Override
    public Collection update(Long id, Collection collection) {
        Set<ConstraintViolation<Collection>> violations = validator.validate(collection);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<Collection> collectionWithName = collectionRepository.findByName(collection.getName());

        if(collectionWithName.isPresent() && !collectionWithName.get().getId().equals(id))
            throw new ResourceValidationException(ENTITY, "An collection with the same name already exists.");

        return collectionRepository.findById(id).map(existingCollection ->
                        collectionRepository.save(existingCollection.withName(collection.getName())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long collectionId) {
        return collectionRepository.findById(collectionId).map(existingCollection -> {
            collectionRepository.delete(existingCollection);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, collectionId));
    }
}
