package com.justinlopez.bloggingapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDTO {

    private Long commentId;

    private String comment;

}
