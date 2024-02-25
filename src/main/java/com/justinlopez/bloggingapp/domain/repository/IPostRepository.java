package com.justinlopez.bloggingapp.domain.repository;

import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.PostResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IPostRepository {

    PostRequestDTO save(PostRequestDTO postRequestDTO);

    Optional<PostRequestDTO> findById(Long id);

    PostResponseDTO getAll(Integer pageNumber, Integer pageSize);

    PostResponseDTO getAllByUser(Long userId);

    PostResponseDTO getAllByCategory(Long categoryId);

    PostResponseDTO getAllByTitle(String title);

    void delete(Long id);

}
