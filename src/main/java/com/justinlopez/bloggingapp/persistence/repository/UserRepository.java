package com.justinlopez.bloggingapp.persistence.repository;

import com.justinlopez.bloggingapp.domain.dto.UserRequestDTO;
import com.justinlopez.bloggingapp.domain.repository.IUserRepository;
import com.justinlopez.bloggingapp.persistence.crud.IUserJpaRepository;
import com.justinlopez.bloggingapp.persistence.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository implements IUserRepository {

    private final IUserJpaRepository iUserJpaRepository;

    private final IUserMapper iUserMapper;

    @Override
    public UserRequestDTO save(UserRequestDTO userRequestDTO) {
        return iUserMapper.toUserDTO(iUserJpaRepository.save(iUserMapper.toUserEntity(userRequestDTO)));
    }

    @Override
    public Optional<UserRequestDTO> findUserById(Long id) {
        return iUserJpaRepository.findById(id).map(iUserMapper::toUserDTO);
    }

    @Override
    public List<UserRequestDTO> getAll() {
        return iUserMapper.toUserDtoList(iUserJpaRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        iUserJpaRepository.deleteById(id);
    }
}
