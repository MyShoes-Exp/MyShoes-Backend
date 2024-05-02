package com.acme.myshoes.platform.shoes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.acme.myshoes.platform.shoes.api.rest.CommentController;
import com.acme.myshoes.platform.shoes.domain.model.Comment;
import com.acme.myshoes.platform.shoes.domain.service.CommentService;
import com.acme.myshoes.platform.shoes.mapping.CommentMapper;
import com.acme.myshoes.platform.shoes.resource.CommentResource;
import com.acme.myshoes.platform.shoes.resource.CreateCommentResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks antes de cada test
    }


    @Test
    public void testCreateComment() {
        // Simular datos
        CreateCommentResource createResource = new CreateCommentResource("Test Comment", 1L);
        Comment comment = new Comment();
        when(commentMapper.toModel(createResource)).thenReturn(comment);
        when(commentService.create(comment)).thenReturn(comment);
        CommentResource expectedResource = new CommentResource();
        when(commentMapper.toResource(comment)).thenReturn(expectedResource);

        // Llamar al método y verificar el resultado
        CommentResource result = commentController.createComment(createResource);
        assertNotNull(result);
        assertEquals(expectedResource, result);
    }

    @Test
    public void testDeleteComment() {

        Long commentId = 1L;
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();
        when(commentService.delete(commentId)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> result = commentController.deleteComment(commentId);
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }
}