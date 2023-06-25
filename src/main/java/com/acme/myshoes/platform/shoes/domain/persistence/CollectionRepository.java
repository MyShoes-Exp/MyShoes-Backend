package com.acme.myshoes.platform.shoes.domain.persistence;

import com.acme.myshoes.platform.shoes.domain.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Optional<Collection> findByName(String name);
    List<Collection> findAllByUser(Long user_id);
}
