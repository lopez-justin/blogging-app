package com.justinlopez.bloggingapp.domain.service;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;
import com.justinlopez.bloggingapp.domain.repository.IUserRepository;
import com.justinlopez.bloggingapp.domain.usecase.IUserUseCase;
import com.justinlopez.bloggingapp.exception.MissingRequiredFieldsException;
import com.justinlopez.bloggingapp.exception.UserNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserUseCase {

    private final IUserRepository iUserRepository;


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if (validateUserFields(userRequestDTO)) {
            throw new MissingRequiredFieldsException();
        }

        return iUserRepository.save(userRequestDTO);

    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        return iUserRepository.findUserById(id).orElseThrow(() -> new UserNotExistException(id.toString()));

    }


    @Override
    public List<UserResponseDTO> getAllUsers() {

        return iUserRepository.getAll();

    }


    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {

        if (validateUserFields(userRequestDTO)) {
            throw new MissingRequiredFieldsException();
        }

        if (iUserRepository.findUserById(userRequestDTO.getId()).isEmpty()) {
            throw new UserNotExistException(userRequestDTO.getId().toString());
        }

        return iUserRepository.save(userRequestDTO);

    }


    @Override
    public boolean deleteUser(Long id) {

        if (iUserRepository.findUserById(id).isEmpty()) {
            throw new UserNotExistException(id.toString());
        }

        iUserRepository.delete(id);
        return true;

    }

    private boolean validateUserFields(UserRequestDTO userRequestDTO) {
        return userRequestDTO.getName() == null || userRequestDTO.getName().isEmpty() ||
                userRequestDTO.getEmail() == null || userRequestDTO.getEmail().isEmpty() ||
                userRequestDTO.getPassword() == null || userRequestDTO.getPassword().isEmpty();
    }

    
}
