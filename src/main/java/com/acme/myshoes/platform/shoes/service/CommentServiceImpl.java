package com.acme.myshoes.platform.shoes.service;

import com.acme.myshoes.platform.shoes.domain.model.Collection;
import com.acme.myshoes.platform.shoes.domain.model.Comment;
import com.acme.myshoes.platform.shoes.domain.persistence.CommentRepository;
import com.acme.myshoes.platform.shoes.domain.service.CommentService;
import com.acme.myshoes.platform.shoes.exception.ResourceNotFoundException;
import com.acme.myshoes.platform.shoes.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;

public class CommentServiceImpl implements CommentService {
    private static final String Entity = "Comment";
    private final CommentRepository commentRepository;
    private final Validator validator;

    public CommentServiceImpl(CommentRepository commentRepository, Validator validator) {
        this.commentRepository = commentRepository;
        this.validator = validator;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Page<Comment> getAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment getById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException(Entity, commentId));
    }

    @Override
    public Comment getByName(String name) {
        return commentRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("No exist a comment with this name"));
    }

    @Override
    public Comment create(Comment comment) {
        Set<ConstraintViolation<Comment>> violations=validator.validate(comment);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if(commentRepository.findByName(comment.getName()).isPresent())
            throw new ResourceValidationException(ENTITY, "An comment with the same name already exists.");
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Long id, Comment comment) {
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<Comment> commentWithName = commentRepository.findByName(comment.getName());

        if(commentWithName.isPresent() && !commentWithName.get().getId().equals(id))
            throw new ResourceValidationException(ENTITY, "An comment with the same name already exists.");

        return commentRepository.findById(id).map(existingCollection ->
                        commentRepository.save(existingCollection.withName(comment.getName())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long commentId) {
        return commentRepository.findById(commentId).map(existingComment -> {
            commentRepository.delete(existingComment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, commentId));
    }
}
