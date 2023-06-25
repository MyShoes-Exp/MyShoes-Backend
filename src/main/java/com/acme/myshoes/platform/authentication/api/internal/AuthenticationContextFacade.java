package com.acme.myshoes.platform.authentication.api.internal;

import com.acme.myshoes.platform.authentication.domain.model.User;

import java.util.Optional;

public interface AuthenticationContextFacade {
    Optional<User> getUserById(Long user_id);
}
