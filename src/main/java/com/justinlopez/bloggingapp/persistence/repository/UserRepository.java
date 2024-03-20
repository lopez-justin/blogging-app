package com.justinlopez.bloggingapp.persistence.repository;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.UserResponseDTO;
import com.justinlopez.bloggingapp.domain.repository.IUserRepository;
import com.justinlopez.bloggingapp.persistence.crud.IUserJpaRepository;
import com.justinlopez.bloggingapp.persistence.entity.UserEntity;
import com.justinlopez.bloggingapp.persistence.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository implements IUserRepository {

    private final IUserJpaRepository iUserJpaRepository;

    private final IUserMapper iUserMapper;

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {

        UserRequestDTO user = iUserMapper.toUserDTO(iUserJpaRepository.save(iUserMapper.toUserEntity(userRequestDTO)));

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        if (user != null) {
            userResponseDTO.setId(user.getId());
            userResponseDTO.setName(user.getName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setAbout(user.getAbout());
        }

        return userResponseDTO;
    }

    @Override
    public Optional<UserResponseDTO> findUserById(Long id) {

        UserEntity userEntity = iUserJpaRepository.findById(id).orElse(null);
        UserRequestDTO user = iUserMapper.toUserDTO(userEntity);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        if (user != null) {
            userResponseDTO.setId(user.getId());
            userResponseDTO.setName(user.getName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setAbout(user.getAbout());
        }

        if (userResponseDTO.getId() == null){
            return Optional.empty();
        } else {
            return Optional.of(userResponseDTO);
        }

    }

    @Override
    public List<UserResponseDTO> getAll() {

        List<UserRequestDTO> users = iUserMapper.toUserDtoList(iUserJpaRepository.findAll());
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();

        for (UserRequestDTO user : users) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setName(user.getName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setAbout(user.getAbout());
            userResponseDTOs.add(userResponseDTO);
        }

        return userResponseDTOs;
    }

    @Override
    public void delete(Long id) {
        iUserJpaRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findByEmail(String username) {
        return Optional.of(iUserJpaRepository.findByEmail(username));
    }

    @Override
    public boolean existsByEmail(String email) {
        return iUserJpaRepository.existsByEmail(email);
    }

}
