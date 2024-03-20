package com.justinlopez.bloggingapp.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthRequestDTO {

    private String email;

    private String password;

}
