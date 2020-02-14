package com.catdog.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsResponseDto2 {
    private String nickname;
    private String img;
    private String contents;
    private List<String> hashtags;
    private String modifiedDate;
    private Long likes;

    @Builder
    public PostsResponseDto2(String nickname, String img,
                             String contents, List<String> hashtags,
                             String modifiedDate ,Long likes) {
        this.nickname = nickname;
        this.img = img;
        this.contents = contents;
        this.hashtags = hashtags;
        this.modifiedDate = modifiedDate;
        this.likes = likes;
    }
}
