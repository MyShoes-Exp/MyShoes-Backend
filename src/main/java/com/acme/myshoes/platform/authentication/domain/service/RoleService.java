package com.acme.myshoes.platform.authentication.domain.service;

import com.acme.myshoes.platform.authentication.domain.model.entity.Role;

import java.util.List;

public interface RoleService {
    void seed();
    List<Role> getAll();
}
