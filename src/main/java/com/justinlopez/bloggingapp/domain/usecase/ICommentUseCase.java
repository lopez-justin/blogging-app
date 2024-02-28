package com.justinlopez.bloggingapp.domain.usecase;

import com.justinlopez.bloggingapp.domain.dto.CommentDTO;
import com.justinlopez.bloggingapp.domain.dto.CommentResponseDTO;

public interface ICommentUseCase {

    CommentResponseDTO createCommnent(CommentDTO commentDTO, Long postId);

    boolean deleteComment(Long id);

}
