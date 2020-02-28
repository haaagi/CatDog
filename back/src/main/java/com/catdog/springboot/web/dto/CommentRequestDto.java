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
    private String contents;
    @Builder
    public CommentRequestDto(Long pid, String nickname, String contents) {
        this.pid = pid;
        this.nickname = nickname;
        this.contents = contents;
    }
    public Comment toEntity(User user, Posts posts, String content) {
        return Comment.builder()
                .posts(posts)
                .user(user)
                .content(content)
                .build();
    }
}
