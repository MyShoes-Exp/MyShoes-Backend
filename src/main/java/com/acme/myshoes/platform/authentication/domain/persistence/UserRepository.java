package com.acme.myshoes.platform.authentication.domain.persistence;

import com.acme.myshoes.platform.authentication.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
