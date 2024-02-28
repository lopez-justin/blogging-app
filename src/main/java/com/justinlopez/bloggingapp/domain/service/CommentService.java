package com.justinlopez.bloggingapp.domain.service;

import com.justinlopez.bloggingapp.domain.dto.CommentDTO;
import com.justinlopez.bloggingapp.domain.dto.CommentResponseDTO;
import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.repository.ICommentRepository;
import com.justinlopez.bloggingapp.domain.repository.IPostRepository;
import com.justinlopez.bloggingapp.domain.usecase.ICommentUseCase;
import com.justinlopez.bloggingapp.exception.CommentNotExistException;
import com.justinlopez.bloggingapp.exception.PostNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService implements ICommentUseCase {

    private final ICommentRepository iCommentRepository;

    private final IPostRepository iPostRepository;

    @Override
    public CommentResponseDTO createCommnent(CommentDTO commentDTO, Long postId) {

        PostRequestDTO postRequestDTO = iPostRepository.findById(postId).orElseThrow(() -> new PostNotExistException(postId.toString()));
        commentDTO.setPost(postRequestDTO);

        return iCommentRepository.save(commentDTO);

    }

    @Override
    public boolean deleteComment(Long id) {

        if (iCommentRepository.findById(id).isEmpty()) {
            throw new CommentNotExistException(id.toString());
        }

        iCommentRepository.delete(id);
        return true;
    }
}
