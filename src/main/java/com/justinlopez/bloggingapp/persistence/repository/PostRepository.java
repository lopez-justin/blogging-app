package com.justinlopez.bloggingapp.persistence.repository;

import com.justinlopez.bloggingapp.domain.dto.CategoryDTO;
import com.justinlopez.bloggingapp.domain.dto.PostRequestDTO;
import com.justinlopez.bloggingapp.domain.dto.PostResponseDTO;
import com.justinlopez.bloggingapp.domain.repository.IPostRepository;
import com.justinlopez.bloggingapp.persistence.crud.IPostJpaRepository;
import com.justinlopez.bloggingapp.persistence.crud.IUserJpaRepository;
import com.justinlopez.bloggingapp.persistence.entity.PostEntity;
import com.justinlopez.bloggingapp.persistence.entity.UserEntity;
import com.justinlopez.bloggingapp.persistence.mapper.ICategoryMapper;
import com.justinlopez.bloggingapp.persistence.mapper.IPostMapper;
import com.justinlopez.bloggingapp.persistence.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostRepository implements IPostRepository {

    private final IPostJpaRepository iPostJpaRepository;

    private final IUserJpaRepository iUserJpaRepository;

    private final IPostMapper iPostMapper;

    private final ICategoryMapper iCategoryMapper;


    @Override
    public PostRequestDTO save(PostRequestDTO postRequestDTO) {
        return iPostMapper.toPostRequestDTO(iPostJpaRepository.save(iPostMapper.toPostEntity(postRequestDTO)));
    }

    @Override
    public Optional<PostRequestDTO> findById(Long id) {
        return iPostJpaRepository.findById(id).map(iPostMapper::toPostRequestDTO);
    }

    @Override
    public PostResponseDTO getAll(Integer pageNumber, Integer pageSize, String sortBy, String sorDir) {

        Sort sort = (sorDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<PostEntity> pagePost = iPostJpaRepository.findAll(p);

        return toPostResponseDTO(pagePost);

    }

    @Override
    public PostResponseDTO getAllByUser(Long userId, Integer pageNumber, Integer pageSize) {

        UserEntity user = iUserJpaRepository.findById(userId).orElse(null);

        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<PostEntity> pagePost = iPostJpaRepository
                .findAllByUser(user, p);

        return toPostResponseDTO(pagePost);

    }

    @Override
    public PostResponseDTO getAllByCategory(CategoryDTO category, Integer pageNumber, Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<PostEntity> pagePost = iPostJpaRepository
                .findAllByCategory(iCategoryMapper.toCategoryEntity(category), p);

        return toPostResponseDTO(pagePost);

    }

    @Override
    public List<PostRequestDTO> getAllByTitle(String keyword) {

        return iPostMapper.toPostRequestDTOs(iPostJpaRepository.findAllByTitleContainingIgnoreCase(keyword));

    }

    @Override
    public void delete(Long id) {
        iPostJpaRepository.deleteById(id);
    }


    private PostResponseDTO toPostResponseDTO(Page<PostEntity> pagePost) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setContent(iPostMapper.toPostRequestDTOs(pagePost.getContent()));
        postResponseDTO.setPageNumber(pagePost.getNumber());
        postResponseDTO.setPageSize(pagePost.getSize());
        postResponseDTO.setTotalElements(pagePost.getTotalElements());
        postResponseDTO.setTotalPages(pagePost.getTotalPages());
        postResponseDTO.setLastPage(pagePost.isLast());
        return postResponseDTO;
    }

}
