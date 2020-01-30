package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.posts.Posts;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostsListResponseDto {
    private Long pid;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;
    private Long likes;

    public PostsListResponseDto(Posts entity) {
        this.pid = entity.getPid();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.likes = entity.getPid();
    }
}
