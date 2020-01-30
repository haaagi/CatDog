package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.likes.Likes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsLikesupRequestDto {
    private Long pid;
    private String email;

    @Builder
    public PostsLikesupRequestDto(Posts posts , User user) {
        this.pid = posts.getPid();
        this.email = user.getEmail();
    }

    public Likes toEntity(User user, Posts posts) {
        return Likes.builder()
                .user(user)
                .posts(posts)
                .build();
    }

}
