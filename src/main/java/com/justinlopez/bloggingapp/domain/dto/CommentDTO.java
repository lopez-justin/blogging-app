package com.justinlopez.bloggingapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDTO {

    private Long commentId;

    private String comment;

    private PostRequestDTO post;
}
