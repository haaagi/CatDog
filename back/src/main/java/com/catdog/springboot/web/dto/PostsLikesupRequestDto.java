package com.catdog.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsLikesupRequestDto {
    private Long pid;
    private String nickname;

    @Builder
    public PostsLikesupRequestDto(Long pid , String nickname) {
        this.pid = pid;
        this.nickname = nickname;
    }


}
