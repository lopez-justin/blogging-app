package com.justinlopez.bloggingapp.persistence.crud;

import com.justinlopez.bloggingapp.persistence.entity.CategoryEntity;
import com.justinlopez.bloggingapp.persistence.entity.PostEntity;
import com.justinlopez.bloggingapp.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostJpaRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByUser(UserEntity user);

    List<PostEntity> findAllByCategory(CategoryEntity category);

    List<PostEntity> findAllByTitleContainingIgnoreCase(String title);

}
