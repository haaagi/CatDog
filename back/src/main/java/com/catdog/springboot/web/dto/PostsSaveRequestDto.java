package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String nickname;
    private String img;
    private String content;
    private String hashtags;

    @Builder
    public PostsSaveRequestDto(String nickname, String img, String content, String hashtags) {
        this.nickname = nickname;
        this.img = img;
        this.content = content;
        this.hashtags = hashtags;
    }

    public Posts toEntity(User user) {
        return Posts.builder()
                .img(img)
                .content(content)
                .user(user)
                .build();
    }
}
