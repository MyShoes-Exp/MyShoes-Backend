package com.acme.myshoes.platform.authentication.api.internal;

import com.acme.myshoes.platform.authentication.domain.model.User;
import com.acme.myshoes.platform.authentication.domain.service.UserService;

import java.util.Optional;

public class AuthenticationContextFacadeImpl implements AuthenticationContextFacade{
    private final UserService userService;

    public AuthenticationContextFacadeImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public Optional<User> getUserById(Long user_id) {
        User user = userService.getById(user_id);
        return Optional.ofNullable(user);
    }

}
