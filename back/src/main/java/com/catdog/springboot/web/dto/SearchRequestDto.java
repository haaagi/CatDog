package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class SearchRequestDto {

    private List<Optional<User>> search_nickname;

    private List<String> search_content;

    public SearchRequestDto(List<Optional<User>> search_nickname, List<String> search_content) {
        this.search_nickname = search_nickname;
        this.search_content = search_content;
    }
}