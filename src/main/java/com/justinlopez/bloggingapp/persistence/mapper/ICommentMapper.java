package com.justinlopez.bloggingapp.persistence.mapper;

import com.justinlopez.bloggingapp.domain.dto.CommentDTO;
import com.justinlopez.bloggingapp.domain.dto.CommentResponseDTO;
import com.justinlopez.bloggingapp.persistence.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IPostMapper.class, IUserMapper.class, ICategoryMapper.class})
public interface ICommentMapper {

    @Mapping(source = "post.postId", target = "postId")
    @Mapping(target = "post", ignore = true)
    CommentEntity toCommentEntity(CommentDTO commentDTO);

    CommentResponseDTO toCommentResponseDTO(CommentEntity commentEntity);

}
