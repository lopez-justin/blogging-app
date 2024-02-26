package com.justinlopez.bloggingapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String about;
}
