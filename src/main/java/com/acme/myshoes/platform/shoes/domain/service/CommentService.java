package com.acme.myshoes.platform.shoes.domain.service;

import com.acme.myshoes.platform.shoes.domain.model.Category;
import com.acme.myshoes.platform.shoes.domain.model.Comment;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Page<Comment> getAll(Pageable pageable);
    Comment getById(Long commentId);
    Comment getByName(String name);
    Comment create(Comment comment);
    Comment update(Long id, Comment comment);
    ResponseEntity<?> delete(Long commentId);

}
