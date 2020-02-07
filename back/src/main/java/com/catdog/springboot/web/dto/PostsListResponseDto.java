package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.comment.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostsListResponseDto {
    private String nickname;
    private String img;
    private String contents;
    private List<String> hashtags;
    private List<Comment> comments;
    private String modifiedDate;
    private Long likes;


    public PostsListResponseDto(String nickname, String img, String contents, List<String> hashtags, List<Comment> comments, String modifiedDate ,Long likes) {
        this.nickname = nickname;
        this.img = img;
        this.contents = contents;
        this.hashtags = hashtags;
        this.comments = comments;
        this.modifiedDate = modifiedDate;
        this.likes = likes;
    }

}
