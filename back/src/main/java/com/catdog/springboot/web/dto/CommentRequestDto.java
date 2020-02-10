package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.comment.Comment;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private Long pid;
    private String nickname;
    private String content;

    @Builder
    public CommentRequestDto(Long pid, String nickname, String content) {
        this.pid = pid;
        this.nickname = nickname;
        this.content = content;
    }

    public Comment toEntity(User user, Posts posts) {
        return Comment.builder()
                .posts(posts)
                .user(user)
                .content(content)
                .build();
    }


}
