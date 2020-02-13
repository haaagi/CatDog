package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.comment.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class PostsResponseDto {
  private List<Comment> comment;
  private boolean islike;

    public PostsResponseDto(List comment, boolean islike) {
        this.comment = comment;
        this.islike = islike;
    }
}
