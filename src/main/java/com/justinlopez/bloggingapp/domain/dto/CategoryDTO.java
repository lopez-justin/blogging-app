package com.justinlopez.bloggingapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryDTO {

    private Long categoryId;

    private String title;

    private String description;

}
