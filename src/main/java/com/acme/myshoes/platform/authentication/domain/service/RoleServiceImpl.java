package com.acme.myshoes.platform.authentication.domain.service;

import com.acme.myshoes.platform.authentication.domain.model.entity.Role;
import com.acme.myshoes.platform.authentication.domain.model.enumeration.Roles;
import com.acme.myshoes.platform.authentication.domain.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    private static String[] DEFAULT_ROLES = {"ROLE_USER", "ROLE_INSTRUCTOR", "ROLE_ADMIN"};
    public void seed() {
        Arrays.stream(DEFAULT_ROLES)
                .forEach(name -> {
                    Roles roleName = Roles.valueOf(name);
                    if(!roleRepository.existsByName(roleName)) {
                        roleRepository.save(new Role().withName(roleName));
                    }
                });
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
