package com.justinlopez.bloggingapp.domain.repository;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    UserResponseDTO save(UserRequestDTO userRequestDTO);

    Optional<UserResponseDTO> findUserById(Long id);

    List<UserResponseDTO> getAll();

    void delete(Long id);

}
