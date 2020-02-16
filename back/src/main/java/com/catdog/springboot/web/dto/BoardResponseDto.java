package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.board.BoardComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    private List<BoardComment> comment;

    @Builder
    public BoardResponseDto( List comment) {
        this.comment = comment;
    }
}
