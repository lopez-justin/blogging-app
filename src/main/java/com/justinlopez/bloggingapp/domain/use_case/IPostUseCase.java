package com.justinlopez.bloggingapp.domain.use_case;

import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.PostResponseDTO;

public interface IPostUseCase {

    PostRequestDTO createPost(PostRequestDTO postRequestDTO, Long userId, Long categoryId);

    PostRequestDTO getPostById(Long id);

    PostResponseDTO getAllPosts();

    PostResponseDTO getAllPostsByUser(Long userId);

    PostResponseDTO getAllPostsByCategory(Long categoryId);

    PostResponseDTO getAllPostsByTitle(String title);

    PostRequestDTO updatePost(PostRequestDTO postRequestDTO);

    boolean deletePost(Long id);

}
