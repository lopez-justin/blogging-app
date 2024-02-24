package com.justinlopez.bloggingapp.persistence.repository;

import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.PostResponseDTO;
import com.justinlopez.bloggingapp.domain.repository.IPostRepository;
import com.justinlopez.bloggingapp.persistence.crud.ICategoryJpaRepository;
import com.justinlopez.bloggingapp.persistence.crud.IPostJpaRepository;
import com.justinlopez.bloggingapp.persistence.entity.PostEntity;
import com.justinlopez.bloggingapp.persistence.mapper.IPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostRepository implements IPostRepository {

    private final IPostJpaRepository iPostJpaRepository;

    private final IPostMapper iPostMapper;


    @Override
    public PostRequestDTO save(PostRequestDTO postRequestDTO) {
        return iPostMapper.toPostRequestDTO(iPostJpaRepository.save(iPostMapper.toPostEntity(postRequestDTO)));
    }

    @Override
    public Optional<PostRequestDTO> findById(Long id) {
        return iPostJpaRepository.findById(id).map(iPostMapper::toPostRequestDTO);
    }

    @Override
    public PostResponseDTO getAll() {
        return null;
    }

    @Override
    public PostResponseDTO getAllByUser(Long userId) {
        return null;
    }

    @Override
    public PostResponseDTO getAllByCategory(Long categoryId) {
        return null;
    }

    @Override
    public PostResponseDTO getAllByTitle(String title) {
        return null;
    }

    @Override
    public void delete(Long id) {
        iPostJpaRepository.deleteById(id);
    }
}