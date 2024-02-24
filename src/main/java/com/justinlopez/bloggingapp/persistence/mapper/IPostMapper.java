package com.justinlopez.bloggingapp.persistence.mapper;

import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.persistence.entity.PostEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IUserMapper.class, ICategoryMapper.class})
public interface IPostMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "category.categoryId", target = "categoryId")
    //@Mapping(target = "user", ignore = true)
    //@Mapping(target = "category", ignore = true)
    PostEntity toPostEntity(PostRequestDTO postRequestDTO);

    // @InheritInverseConfiguration
    PostRequestDTO toPostRequestDTO(PostEntity postEntity);


    List<PostRequestDTO> toPostRequestDTOs(List<PostEntity> postEntities);

}
