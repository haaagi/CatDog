package com.catdog.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String contents;

    @Builder
    public BoardUpdateRequestDto(String contents) {
        this.contents = contents;
    }
}
