package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.hashtag.Hashtags;
import com.catdog.springboot.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
public class SearchRequestDto {

    private List<Optional<User>> search_nickname;

    private List<SearchHashTagDto> search_content; // 게시글 숫자들

    public SearchRequestDto(List<Optional<User>> search_nickname, List<SearchHashTagDto> search_content) {
        this.search_nickname = search_nickname;
        this.search_content = search_content;
    }
}