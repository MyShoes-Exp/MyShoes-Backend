package com.acme.myshoes.platform.authentication.api.rest;

import com.acme.myshoes.platform.authentication.domain.service.UserService;
import com.acme.myshoes.platform.authentication.mapping.UserMapper;
import com.acme.myshoes.platform.authentication.resource.CreateUserResource;
import com.acme.myshoes.platform.authentication.resource.UpdateUserResource;
import com.acme.myshoes.platform.authentication.resource.UserResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListPage(userService.getAll(), pageable);
    }
    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return mapper.toResource(userService.getById(userId));
    }
    @PostMapping
    public ResponseEntity<UserResource> createUser(CreateUserResource resource) {
        return new ResponseEntity<>(mapper.toResource(userService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }
    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId,
                                   @RequestBody UpdateUserResource resource) {
        return mapper.toResource(userService.update(userId, mapper.toModel(resource)));
    }
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }
}
