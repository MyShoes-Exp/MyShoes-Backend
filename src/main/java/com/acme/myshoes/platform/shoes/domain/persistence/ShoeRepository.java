package com.acme.myshoes.platform.shoes.domain.persistence;

import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {
    List<Shoe> findByCollectionName(String name);
    List<Shoe> findByCollectionId(Long collectionId);
    Optional<Shoe> findByName(String name);
}
