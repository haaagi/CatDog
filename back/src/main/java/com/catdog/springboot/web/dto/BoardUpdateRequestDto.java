package com.catdog.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String contents;

    @Builder
    public BoardUpdateRequestDto(String contents, String title) {
        this.title = title;
        this.contents = contents;
    }
}
