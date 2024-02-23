package com.justinlopez.bloggingapp.domain.repository;

import com.justinlopez.bloggingapp.domain.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {

    CategoryDTO save(CategoryDTO categoryDTO);

    Optional<CategoryDTO> findById(Long id);

    List<CategoryDTO> getAll();

    void delete(Long id);

}
