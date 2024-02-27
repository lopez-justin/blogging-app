package com.justinlopez.bloggingapp.domain.usecase;

import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.PostResponseDTO;

import java.util.List;

public interface IPostUseCase {

    PostRequestDTO createPost(PostRequestDTO postRequestDTO, Long userId, Long categoryId);

    PostRequestDTO getPostById(Long id);

    PostResponseDTO getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostResponseDTO getAllPostsByUser(Long userId, Integer pageNumber, Integer pageSize);

    PostResponseDTO getAllPostsByCategory(Long categoryId, Integer pageNumber, Integer pageSize);

    List<PostRequestDTO> getAllPostsByTitle(String keyword);

    PostRequestDTO updatePost(PostRequestDTO postRequestDTO);

    boolean deletePost(Long id);

}
