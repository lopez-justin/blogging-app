package com.justinlopez.bloggingapp.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private String comment;

    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private PostEntity post;

}
