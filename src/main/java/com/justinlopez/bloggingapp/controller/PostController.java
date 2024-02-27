package com.justinlopez.bloggingapp.controller;

import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.PostResponseDTO;
import com.justinlopez.bloggingapp.domain.usecase.IPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Update
    @PatchMapping("/posts")
    public ResponseEntity<PostRequestDTO> updatePost(@RequestBody PostRequestDTO postRequestDTO) {

        return ResponseEntity.ok(iPostUseCase.updatePost(postRequestDTO));

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
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        return ResponseEntity.ok(iPostUseCase.getAllPosts(pageNumber, pageSize, sortBy, sortDir));

    }

    // Search by title
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostRequestDTO>> searchPostsByTitle(@PathVariable String keyword) {

        return ResponseEntity.ok(iPostUseCase.getAllPostsByTitle(keyword));

    }

    // Delete
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {

        return new ResponseEntity<>(iPostUseCase.deletePost(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

}
