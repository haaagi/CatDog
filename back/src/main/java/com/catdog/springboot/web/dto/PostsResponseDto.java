package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String img;
    private String content;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getPid();
        this.img = entity.getImg();
        this.content = entity.getContent();
    }
}
