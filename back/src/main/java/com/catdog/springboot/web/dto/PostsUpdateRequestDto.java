package com.catdog.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String img;
    private String content;
    private List<String> hashtags;

    @Builder
    public PostsUpdateRequestDto(String img, String content, List hashtags) {
        this.img = img;
        this.content = content;
        this.hashtags = hashtags;
    }


}