package com.justinlopez.bloggingapp.controller;

import com.justinlopez.bloggingapp.domain.dto.CommentDTO;
import com.justinlopez.bloggingapp.domain.dto.CommentResponseDTO;
import com.justinlopez.bloggingapp.domain.usecase.ICommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class CommentController {

    private final ICommentUseCase iCommentUseCase;

    @PostMapping("post/{postId}/comment")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCommentUseCase.createCommnent(commentDTO, postId));
    }

    @DeleteMapping("comments/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {
        return new ResponseEntity<>(iCommentUseCase.deleteComment(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
