package com.justinlopez.bloggingapp.domain.service;

import com.justinlopez.bloggingapp.domain.dto.*;
import com.justinlopez.bloggingapp.domain.repository.ICategoryRepository;
import com.justinlopez.bloggingapp.domain.repository.IPostRepository;
import com.justinlopez.bloggingapp.domain.repository.IUserRepository;
import com.justinlopez.bloggingapp.domain.use_case.IPostUseCase;
import com.justinlopez.bloggingapp.exception.CategoryNotExistException;
import com.justinlopez.bloggingapp.exception.PostNotExistException;
import com.justinlopez.bloggingapp.exception.UserNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PostService implements IPostUseCase {

    private final IPostRepository iPostRepository;

    private final IUserRepository iUserRepository;

    private final ICategoryRepository iCategoryRepository;

    @Override
    public PostRequestDTO createPost(PostRequestDTO postRequestDTO, Long userId, Long categoryId) {

        UserResponseDTO user = iUserRepository.findUserById(userId).orElse(null);
        CategoryDTO category = iCategoryRepository.findById(categoryId).orElse(null);

        if (user == null) {
            throw new UserNotExistException(userId.toString());
        }

        if (category == null) {
            throw new CategoryNotExistException(categoryId.toString());
        }

        postRequestDTO.setAddedDate(LocalDate.now());
        postRequestDTO.setUser(user);
        postRequestDTO.setCategory(category);
        return iPostRepository.save(postRequestDTO);

    }

    @Override
    public PostRequestDTO getPostById(Long id) {

        return iPostRepository.findById(id).orElseThrow(() -> new PostNotExistException(id.toString()));

    }

    @Override
    public PostResponseDTO getAllPosts(Integer pageNumber, Integer pageSize) {

        return iPostRepository.getAll(pageNumber, pageSize);

    }

    @Override
    public PostResponseDTO getAllPostsByUser(Long userId) {
        return null;
    }

    @Override
    public PostResponseDTO getAllPostsByCategory(Long categoryId) {
        return null;
    }

    @Override
    public PostResponseDTO getAllPostsByTitle(String title) {
        return null;
    }

    @Override
    public PostRequestDTO updatePost(PostRequestDTO postRequestDTO) {

        if (iPostRepository.findById(postRequestDTO.getPostId()).isEmpty()) {
            throw new PostNotExistException(postRequestDTO.getPostId().toString());
        }

        return iPostRepository.save(postRequestDTO);

    }

    @Override
    public boolean deletePost(Long id) {

        if (iPostRepository.findById(id).isEmpty()) {
            return false;
        }

        iPostRepository.delete(id);
        return true;

    }
}
