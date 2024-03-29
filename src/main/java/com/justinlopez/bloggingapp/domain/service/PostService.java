package com.justinlopez.bloggingapp.domain.service;

import com.justinlopez.bloggingapp.domain.dto.*;
import com.justinlopez.bloggingapp.domain.repository.ICategoryRepository;
import com.justinlopez.bloggingapp.domain.repository.IPostRepository;
import com.justinlopez.bloggingapp.domain.repository.IUserRepository;
import com.justinlopez.bloggingapp.domain.usecase.IPostUseCase;
import com.justinlopez.bloggingapp.exception.CategoryNotExistException;
import com.justinlopez.bloggingapp.exception.PostNotExistException;
import com.justinlopez.bloggingapp.exception.UserNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public PostResponseDTO getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        return iPostRepository.getAll(pageNumber, pageSize, sortBy, sortDir);

    }

    @Override
    public PostResponseDTO getAllPostsByUser(Long userId, Integer pageNumber, Integer pageSize) {

        if (iUserRepository.findUserById(userId).isEmpty()) {
            throw new UserNotExistException(userId.toString());
        }

        return iPostRepository.getAllByUser(
                userId,
                pageNumber,
                pageSize
        );

    }

    @Override
    public PostResponseDTO getAllPostsByCategory(Long categoryId, Integer pageNumber, Integer pageSize) {

        CategoryDTO categoryDTO = iCategoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new CategoryNotExistException(categoryId.toString()));

        return iPostRepository.getAllByCategory(
                categoryDTO,
                pageNumber,
                pageSize);

    }

    @Override
    public List<PostRequestDTO> getAllPostsByTitle(String keyword) {

        return iPostRepository.getAllByTitle(keyword);

    }

    @Override
    public PostRequestDTO updatePost(PostRequestDTO postRequestDTO) {

        PostRequestDTO post = iPostRepository.findById(postRequestDTO.getPostId())
                .orElseThrow(() -> new PostNotExistException(postRequestDTO.getPostId().toString()));
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        post.setImage(postRequestDTO.getImage());

        return iPostRepository.save(post);

    }

    @Override
    public boolean deletePost(Long id) {

        if (iPostRepository.findById(id).isEmpty()) {
            throw new PostNotExistException(id.toString());
        }

        iPostRepository.delete(id);
        return true;

    }
}
