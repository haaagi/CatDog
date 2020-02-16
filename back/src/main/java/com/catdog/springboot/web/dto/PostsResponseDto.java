package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
  private List<Comment> comment;
  private boolean islike;

    @Builder
    public PostsResponseDto(List comment, boolean islike) {
        this.comment = comment;
        this.islike = islike;
    }
}
