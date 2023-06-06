package com.acme.myshoes.platform.shoes.domain.service;

import com.acme.myshoes.platform.authentication.domain.model.User;
import com.acme.myshoes.platform.shoes.domain.persistence.UserRepository;
import com.acme.myshoes.platform.shoes.exception.ResourceNotFoundException;
import com.acme.myshoes.platform.shoes.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private static final String ENTITY = "User";
    private final UserRepository userRepository;
    private final Validator validator;
    public UserServiceImpl(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public Page<User> getAl(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY, userId));
    }
    @Override
    public User create(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.save(user);
    }
    @Override
    public User update(Long userId, User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(userToUpdate ->
                        userRepository.save(
                                userToUpdate.withName(user.getName())
                                        .withAddress(user.getAddress())
                                        .withEmail(user.getEmail())
                                        .withPassword(user.getPassword())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();})
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

}
