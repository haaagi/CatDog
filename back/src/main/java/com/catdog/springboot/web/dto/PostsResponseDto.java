package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.comment.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class PostsResponseDto {
  private List<Comment> comment;

    public PostsResponseDto(List comment) {
        this.comment = comment;
    }
}
