package com.catdog.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    private String nickname;
    private String contents;

    @Builder
    public BoardResponseDto(String nickname, String contents) {
        this.nickname = nickname;
        this.contents = contents;
    }
}
