package com.acme.myshoes.platform.shoes.api.rest;

import com.acme.myshoes.platform.shoes.domain.service.CommentService;
import com.acme.myshoes.platform.shoes.mapping.CommentMapper;
import com.acme.myshoes.platform.shoes.resource.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/comments", produces = "application/json")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping
    public Page<CommentResource> getAllComments(Pageable pageable) {
        return commentMapper.modelListPage(commentService.getAll(), pageable);
    }
    @GetMapping("{commentId}")
    public CommentResource getCommentById(@PathVariable Long commentId) {
        return commentMapper.toResource(commentService.getById(commentId));
    }

    @PostMapping
    public CommentResource createComment(@RequestBody CreateCommentResource resource) {
        return commentMapper.toResource(commentService.create(commentMapper.toModel(resource)));
    }

    @PutMapping("{commentId}")
    public CommentResource updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentResource resource) {
        return commentMapper.toResource(commentService.update(commentId, commentMapper.toModel(resource)));
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return commentService.delete(commentId);
    }

}
