package com.catdog.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostsListResponseDto {
    private Long pid;
    private String nickname;
    private String profileimg;
    private String img;
    private String contents;
    private List<String> hashtags;
    private String modifiedDate;
    private Long likes;

    @Builder
    public PostsListResponseDto(Long pid, String nickname, String profileimg, String img, String contents, List<String> hashtags, String modifiedDate ,Long likes) {
        this.pid = pid;
        this.nickname = nickname;
        this.profileimg = profileimg;
        this.img = img;
        this.contents = contents;
        this.hashtags = hashtags;
        this.modifiedDate = modifiedDate;
        this.likes = likes;
    }

}
