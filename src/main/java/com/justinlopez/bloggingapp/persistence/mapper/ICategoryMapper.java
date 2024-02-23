package com.justinlopez.bloggingapp.persistence.mapper;

import com.justinlopez.bloggingapp.domain.dto.CategoryDTO;
import com.justinlopez.bloggingapp.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

    CategoryDTO toCategoryDTO(CategoryEntity categoryEntity);

    CategoryEntity toCategoryEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toCategoryDtoList(List<CategoryEntity> categoryEntityList);

}
