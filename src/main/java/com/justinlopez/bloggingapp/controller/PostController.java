package com.justinlopez.bloggingapp.controller;

import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.use_case.IPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class PostController {

    private final IPostUseCase iPostUseCase;


    // Create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostRequestDTO> createPost(
            @RequestBody PostRequestDTO postRequestDTO,
            @PathVariable Long userId,
            @PathVariable Long categoryId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iPostUseCase.createPost(postRequestDTO, userId, categoryId));

    }

    // Delete
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {

        return new ResponseEntity<>(iPostUseCase.deletePost(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

}
