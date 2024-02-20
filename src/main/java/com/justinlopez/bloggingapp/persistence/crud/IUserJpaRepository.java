package com.justinlopez.bloggingapp.persistence.crud;

import com.justinlopez.bloggingapp.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserJpaRepository extends JpaRepository<UserEntity, Long> {



}
