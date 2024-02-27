package com.justinlopez.bloggingapp.domain.usecase;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;

import java.util.List;

public interface IUserUseCase {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(UserRequestDTO userRequestDTO);

    boolean deleteUser(Long id);

}
