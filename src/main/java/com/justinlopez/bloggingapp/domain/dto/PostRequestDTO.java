package com.justinlopez.bloggingapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
public class PostRequestDTO {

    private Long postId;

    private String title;

    private String content;

    private String image;

    private LocalDate addedDate;

    private UserResponseDTO user;

    private CategoryDTO category;

    private Set<CommentResponseDTO> comments;

}
