package com.justinlopez.bloggingapp.domain.service;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;
import com.justinlopez.bloggingapp.domain.repository.IUserRepository;
import com.justinlopez.bloggingapp.domain.use_case.IUserUseCase;
import com.justinlopez.bloggingapp.exception.UserNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserUseCase {

    private final IUserRepository iUserRepository;


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        UserRequestDTO savedUser = iUserRepository.save(userRequestDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(savedUser.getId());
        userResponseDTO.setName(savedUser.getName());
        userResponseDTO.setEmail(savedUser.getEmail());
        userResponseDTO.setAbout(savedUser.getAbout());

        return userResponseDTO;

    }


    @Override
    public Optional<UserRequestDTO> getUserById(Long id) {

        // Return exception if user does not exist
        if (iUserRepository.findUserById(id).isEmpty()) {
            throw new UserNotExistException(id.toString());
        }

        return iUserRepository.findUserById(id);

    }


    @Override
    public List<UserRequestDTO> getAllUsers() {

        return iUserRepository.getAll();

    }


    @Override
    public Optional<UserRequestDTO> updateUser(UserRequestDTO userRequestDTO) {

        if (iUserRepository.findUserById(userRequestDTO.getId()).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(iUserRepository.save(userRequestDTO));

    }


    @Override
    public boolean deleteUser(Long id) {

        if (iUserRepository.findUserById(id).isPresent()) {
            iUserRepository.delete(id);
            return true;
        }

        return false;

    }

    
}
