package com.justinlopez.bloggingapp.domain.service;

import com.justinlopez.bloggingapp.domain.dto.CategoryDTO;
import com.justinlopez.bloggingapp.domain.repository.ICategoryRepository;
import com.justinlopez.bloggingapp.domain.use_case.ICategoryUseCase;
import com.justinlopez.bloggingapp.exception.CategoryNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryUseCase {

    private final ICategoryRepository iCategoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return iCategoryRepository.save(categoryDTO);
    }


    @Override
    public CategoryDTO getCategoryById(Long id) {

        return iCategoryRepository.findById(id).orElseThrow(() -> new CategoryNotExistException(id.toString()));

    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        return iCategoryRepository.getAll();
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {

        if (iCategoryRepository.findById(categoryDTO.getCategoryId()).isEmpty()) {
            throw new CategoryNotExistException(categoryDTO.getCategoryId().toString());
        }

        return iCategoryRepository.save(categoryDTO);

    }

    @Override
    public boolean deleteCategory(Long id) {

        if (iCategoryRepository.findById(id).isEmpty()) {
            return false;
        }

        iCategoryRepository.delete(id);
        return true;

    }


}
