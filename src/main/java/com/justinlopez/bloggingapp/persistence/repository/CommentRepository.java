package com.justinlopez.bloggingapp.persistence.repository;

import com.justinlopez.bloggingapp.domain.dto.CommentDTO;
import com.justinlopez.bloggingapp.domain.dto.CommentResponseDTO;
import com.justinlopez.bloggingapp.domain.repository.ICommentRepository;
import com.justinlopez.bloggingapp.persistence.crud.ICommentJpaRepository;
import com.justinlopez.bloggingapp.persistence.mapper.ICommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepository implements ICommentRepository {

    private final ICommentJpaRepository iCommentJpaRepository;

    private final ICommentMapper iCommentMapper;

    @Override
    public CommentResponseDTO save(CommentDTO commentDTO) {
        return iCommentMapper.toCommentResponseDTO(
                iCommentJpaRepository.save(
                        iCommentMapper.toCommentEntity(commentDTO)));
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        return iCommentJpaRepository.findById(id).map(iCommentMapper::toCommentResponseDTO);
    }

    @Override
    public void delete(Long id) {
        iCommentJpaRepository.deleteById(id);
    }
}
