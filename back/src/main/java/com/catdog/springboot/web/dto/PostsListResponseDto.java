package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.comment.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostsListResponseDto {
    private String nickname;
    private String img;
    private String contents;
    private List<String> hashtags;
    private List<Comment> comments;
    private LocalDateTime modifiedDate;
    private Long likes;

    public PostsListResponseDto(Object entity) {
//        this.nickname = entity.get();
//        this.img = entity.getImg();
//        this.modifiedDate = entity.getModifiedDate();
//        this.likes = entity.getPid();
    }
}
