package com.justinlopez.bloggingapp.persistence.crud;

import com.justinlopez.bloggingapp.persistence.entity.CategoryEntity;
import com.justinlopez.bloggingapp.persistence.entity.PostEntity;
import com.justinlopez.bloggingapp.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostJpaRepository extends JpaRepository<PostEntity, Long> {

    Page<PostEntity> findAllByUser(UserEntity user, Pageable pageable);

    Page<PostEntity> findAllByCategory(CategoryEntity category, Pageable pageable);

    List<PostEntity> findAllByTitleContainingIgnoreCase(String title);

}
