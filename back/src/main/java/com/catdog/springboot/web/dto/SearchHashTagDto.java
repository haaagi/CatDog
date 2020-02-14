package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class SearchHashTagDto {

    private String content;

    private Long content_cnt;

    @Builder
    public SearchHashTagDto(String search_nickname, Long search_content) {
        this.content = search_nickname;
        this.content_cnt = search_content;
    }
}
