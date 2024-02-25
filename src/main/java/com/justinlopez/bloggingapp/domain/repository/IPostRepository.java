package com.justinlopez.bloggingapp.domain.repository;

import com.justinlopez.bloggingapp.domain.dto.*;
import com.justinlopez.bloggingapp.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IPostRepository {

    PostRequestDTO save(PostRequestDTO postRequestDTO);

    Optional<PostRequestDTO> findById(Long id);

    PostResponseDTO getAll(Integer pageNumber, Integer pageSize);

    PostResponseDTO getAllByUser(Long userId, Integer pageNumber, Integer pageSize);

    PostResponseDTO getAllByCategory(CategoryDTO category, Integer pageNumber, Integer pageSize);

    PostResponseDTO getAllByTitle(String title);

    void delete(Long id);

}
