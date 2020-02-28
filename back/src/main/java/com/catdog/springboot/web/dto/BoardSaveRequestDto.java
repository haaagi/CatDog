package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.board.Board;
import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String contents;
    private String nickname;
    private Long type;

    @Builder
    public BoardSaveRequestDto(String title, String contents, String nickname, Long type) {
        this.nickname = nickname;
        this.contents = contents;
        this.title = title;
        this.type = type;
    }

    public Board toEntity(User user) {
        return Board.builder()
                .type(type)
                .title(title)
                .contents(contents)
                .user(user)
                .build();
    }
}
