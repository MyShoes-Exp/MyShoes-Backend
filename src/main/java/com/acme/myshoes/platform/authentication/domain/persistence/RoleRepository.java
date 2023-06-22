package com.acme.myshoes.platform.authentication.domain.persistence;

import com.acme.myshoes.platform.authentication.domain.model.entity.Role;
import com.acme.myshoes.platform.authentication.domain.model.enumeration.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
    boolean existsByName(Roles name);
}
