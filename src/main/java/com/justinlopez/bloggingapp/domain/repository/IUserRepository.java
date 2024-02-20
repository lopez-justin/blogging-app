package com.justinlopez.bloggingapp.domain.repository;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    UserRequestDTO save(UserRequestDTO userRequestDTO);

    Optional<UserRequestDTO> findUserById(Long id);

    List<UserRequestDTO> getAll();

    void delete(Long id);

}
