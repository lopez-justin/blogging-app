package com.justinlopez.bloggingapp.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PostResponseDTO {

    private List<PostRequestDTO> content;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

    private Boolean lastPage;

}
