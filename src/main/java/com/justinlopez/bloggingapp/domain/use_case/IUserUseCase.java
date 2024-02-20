package com.justinlopez.bloggingapp.domain.use_case;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IUserUseCase {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    Optional<UserRequestDTO> getUserById(Long id);

    List<UserRequestDTO> getAllUsers();

    Optional<UserRequestDTO> updateUser(UserRequestDTO userRequestDTO);

    boolean deleteUser(Long id);

}
