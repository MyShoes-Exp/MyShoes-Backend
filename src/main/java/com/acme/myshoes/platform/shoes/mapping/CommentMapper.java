package com.acme.myshoes.platform.shoes.mapping;

import com.acme.myshoes.platform.shared.mapping.EnhancedModelMapper;
import com.acme.myshoes.platform.shoes.domain.model.Category;
import com.acme.myshoes.platform.shoes.domain.model.Comment;
import com.acme.myshoes.platform.shoes.domain.model.Shoe;
import com.acme.myshoes.platform.shoes.resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CommentMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public CommentResource toResource(Comment model){
        return mapper.map(model, CommentResource.class);
    }
    public Page<CommentResource> modelListPage(List<Comment> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, CommentResource.class), pageable, modelList.size());
    }

    public List<CommentResource> modelListResource(List<Comment> modelList){
        return mapper.mapList(modelList, CommentResource.class);
    }

    public Comment toModel(CreateCommentResource resource){
        return mapper.map(resource, Comment.class);
    }

    public Comment toModel(UpdateCommentResource resource){
        return mapper.map(resource, Comment.class);
    }

}
