package com.justinlopez.bloggingapp.persistence.crud;

import com.justinlopez.bloggingapp.persistence.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentJpaRepository extends JpaRepository<CommentEntity, Long> {



}
