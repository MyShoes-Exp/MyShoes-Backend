package com.acme.myshoes.platform.shopping.service;

import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.acme.myshoes.platform.shoes.exception.ResourceNotFoundException;
import com.acme.myshoes.platform.shoes.exception.ResourceValidationException;
import com.acme.myshoes.platform.shopping.domain.model.ShoppingCart;
import com.acme.myshoes.platform.shopping.domain.persistence.ShoppingCartRepository;
import com.acme.myshoes.platform.shopping.domain.service.ShoppingCartService;
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
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final String ENTITY = "ShoppingCart";
    private final ShoppingCartRepository shoppingCartRepository;
    private final Validator validator;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, Validator validator) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.validator = validator;
    }

    @Override
    public List<ShoppingCart> getAll(){
        return shoppingCartRepository.findAll();
    }

    @Override
    public Page<ShoppingCart> getAll(Pageable pageable){
        return shoppingCartRepository.findAll(pageable);
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart){
        Set<ConstraintViolation<ShoppingCart>> violations = validator.validate(shoppingCart);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return shoppingCartRepository.save(shoppingCart);
    }


    @Override
    public ResponseEntity<?> delete(Long shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).map(shoppingCart -> {
            shoppingCartRepository.delete(shoppingCart);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, shoppingCartId));
    }

    public void AddShoesToCart(Long shoppingCartId, Shoe shoe) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, shoppingCartId));
        shoppingCart.addShoe(shoe);
        shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getById(Long shoppingcartId){
        return shoppingCartRepository.findById(shoppingcartId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, shoppingcartId));
    }

    @Override
    public ShoppingCart update(Long id, ShoppingCart shoppingCart){
       Set<ConstraintViolation<ShoppingCart>>violations = validator.validate(shoppingCart);
         if(!violations.isEmpty())
              throw new ResourceValidationException(ENTITY, violations);
        Optional<ShoppingCart> shoppingCartWithId = shoppingCartRepository.findById(id);

        if(shoppingCartWithId.isPresent() && !shoppingCartWithId.get().getId().equals(id))
            throw new ResourceValidationException(ENTITY, "A shopping cart with id " + id + " does not exist");

        return shoppingCartRepository.findById(id).map(existingShoppingCart ->
                    shoppingCartRepository.save(existingShoppingCart.withId(shoppingCart.getId())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }


}