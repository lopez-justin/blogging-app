package com.justinlopez.bloggingapp.persistence.repository;

import com.justinlopez.bloggingapp.domain.dto.CategoryDTO;
import com.justinlopez.bloggingapp.domain.repository.ICategoryRepository;
import com.justinlopez.bloggingapp.persistence.crud.ICategoryJpaRepository;
import com.justinlopez.bloggingapp.persistence.crud.IUserJpaRepository;
import com.justinlopez.bloggingapp.persistence.mapper.ICategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryRepository implements ICategoryRepository {

    private final ICategoryJpaRepository iCategoryJpaRepository;

    private final ICategoryMapper iCategoryMapper;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return iCategoryMapper.toCategoryDTO(iCategoryJpaRepository.save(iCategoryMapper.toCategoryEntity(categoryDTO)));
    }

    @Override
    public Optional<CategoryDTO> findById(Long id) {
        return iCategoryJpaRepository.findById(id).map(iCategoryMapper::toCategoryDTO);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return iCategoryMapper.toCategoryDtoList(iCategoryJpaRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        iCategoryJpaRepository.deleteById(id);
    }
}
