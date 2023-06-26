package com.acme.myshoes.platform.shoes.api.rest;

import com.acme.myshoes.platform.shoes.domain.service.CommentService;
import com.acme.myshoes.platform.shoes.mapping.CommentMapper;
import com.acme.myshoes.platform.shoes.resource.CommentResource;
import com.acme.myshoes.platform.shoes.resource.ShoeResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
