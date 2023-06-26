package com.acme.myshoes.platform.shoes.domain.persistence;

import com.acme.myshoes.platform.shoes.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByName(String name);
}
