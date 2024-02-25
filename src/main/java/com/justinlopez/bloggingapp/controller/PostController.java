package com.justinlopez.bloggingapp.controller;

import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.PostResponseDTO;
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

    // Get by id
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostRequestDTO> getPostById(@PathVariable Long id) {

        return ResponseEntity.ok(iPostUseCase.getPostById(id));

    }

    // Get by user id
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponseDTO> getPostsByUserId(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @PathVariable Long userId) {

        return ResponseEntity.ok(iPostUseCase.getAllPostsByUser(userId, pageNumber, pageSize));

    }

    // Get by category id
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponseDTO> getPostByCategoryId(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @PathVariable Long categoryId) {

        return ResponseEntity.ok(iPostUseCase.getAllPostsByCategory(categoryId, pageNumber, pageSize));

    }

    // Get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponseDTO> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        return ResponseEntity.ok(iPostUseCase.getAllPosts(pageNumber, pageSize));

    }

    // Delete
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {

        return new ResponseEntity<>(iPostUseCase.deletePost(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

}
