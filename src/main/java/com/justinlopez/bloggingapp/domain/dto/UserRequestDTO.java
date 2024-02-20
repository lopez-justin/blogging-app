package com.justinlopez.bloggingapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String about;

}
