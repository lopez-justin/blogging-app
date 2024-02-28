package com.justinlopez.bloggingapp.domain.repository;

import com.justinlopez.bloggingapp.domain.dto.CommentDTO;
import com.justinlopez.bloggingapp.domain.dto.CommentResponseDTO;

import java.util.Optional;

public interface ICommentRepository {

    CommentResponseDTO save(CommentDTO commentDTO);

    Optional<CommentResponseDTO> findById(Long id);

    void delete(Long id);

}
