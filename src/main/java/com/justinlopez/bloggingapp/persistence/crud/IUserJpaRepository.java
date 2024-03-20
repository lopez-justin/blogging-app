package com.justinlopez.bloggingapp.persistence.crud;

import com.justinlopez.bloggingapp.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserJpaRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);

}
