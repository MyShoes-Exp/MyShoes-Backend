package com.acme.myshoes.platform.authentication.domain.service;

import com.acme.myshoes.platform.authentication.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAll();
    Page<User> getAll(Pageable pageable);
    User getById(Long userId);
    User create(User user);
    User update(Long userId, User user);
    ResponseEntity<?> delete(Long userId);
}
