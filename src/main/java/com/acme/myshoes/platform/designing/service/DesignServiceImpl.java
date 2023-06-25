package com.acme.myshoes.platform.designing.service;

import com.acme.myshoes.platform.shared.exception.ResourceNotFoundException;
import com.acme.myshoes.platform.shared.exception.ResourceValidationException;
import com.acme.myshoes.platform.designing.domain.model.Design;
import com.acme.myshoes.platform.designing.domain.persistence.DesignRepository;
import com.acme.myshoes.platform.designing.domain.service.DesignService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DesignServiceImpl implements DesignService {

    private static final String ENTITY = "Design";
    private final DesignRepository designRepository;
    private final Validator validator;

    public DesignServiceImpl(DesignRepository designRepository, Validator validator) {
        this.designRepository = designRepository;
        this.validator = validator;
    }

    @Override
    public List<Design> getAll(){
        return designRepository.findAll();
    }

    @Override
    public Page<Design> getAll(Pageable pageable){
        return designRepository.findAll(pageable);
    }

    @Override
    public Design create(Design design){
        Set<ConstraintViolation<Design>> violations = validator.validate(design);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return designRepository.save(design);
    }


    @Override
    public ResponseEntity<?> delete(Long designId) {
        return designRepository.findById(designId).map(design -> {
            designRepository.delete(design);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, designId));
    }


    public Design getById(Long designId){
        return designRepository.findById(designId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, designId));
    }

    @Override
    public Design update(Long id, Design design){
       Set<ConstraintViolation<Design>>violations = validator.validate(design);
         if(!violations.isEmpty())
              throw new ResourceValidationException(ENTITY, violations);
        Optional<Design> designWithId = designRepository.findById(id);

        if(designWithId.isPresent() && !designWithId.get().getId().equals(id))
            throw new ResourceValidationException(ENTITY, "A shopping cart with id " + id + " does not exist");

        return designRepository.findById(id).map(existingDesign ->
                    designRepository.save(existingDesign.withId(design.getId())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }


}
