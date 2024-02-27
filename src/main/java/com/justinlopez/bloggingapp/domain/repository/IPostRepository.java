package com.justinlopez.bloggingapp.domain.repository;

import com.justinlopez.bloggingapp.domain.dto.*;
import com.justinlopez.bloggingapp.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IPostRepository {

    PostRequestDTO save(PostRequestDTO postRequestDTO);

    Optional<PostRequestDTO> findById(Long id);

    PostResponseDTO getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostResponseDTO getAllByUser(Long userId, Integer pageNumber, Integer pageSize);

    PostResponseDTO getAllByCategory(CategoryDTO category, Integer pageNumber, Integer pageSize);

    List<PostRequestDTO> getAllByTitle(String keyword);

    void delete(Long id);

}
