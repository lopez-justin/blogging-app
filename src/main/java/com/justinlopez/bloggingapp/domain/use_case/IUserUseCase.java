package com.justinlopez.bloggingapp.domain.use_case;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IUserUseCase {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(UserRequestDTO userRequestDTO);

    boolean deleteUser(Long id);

}
