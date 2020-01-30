package com.catdog.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsLikesResponseDto {
    private Long pid;
    private Long count;

    @Builder
    public PostsLikesResponseDto(Long pid, Long count) {
        this.pid = pid;
        this.count = count;
    }

}