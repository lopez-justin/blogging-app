package com.justinlopez.bloggingapp.domain.dto;

import com.justinlopez.bloggingapp.persistence.entity.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String about;

    private String role;


}
