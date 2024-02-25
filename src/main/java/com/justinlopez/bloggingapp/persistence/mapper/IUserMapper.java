package com.justinlopez.bloggingapp.persistence.mapper;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;
import com.justinlopez.bloggingapp.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    UserRequestDTO toUserDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(UserResponseDTO userResponseDTO);

    List<UserRequestDTO> toUserDtoList(List<UserEntity> userEntityList);

}
