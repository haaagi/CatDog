package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.board.Board;
import com.catdog.springboot.domain.board.BoardComment;
import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardCommentRequestDto {
    private Long bid;
    private String nickname;
    private String contents;
    @Builder
    public BoardCommentRequestDto(Long pid, String nickname, String contents) {
        this.bid = pid;
        this.nickname = nickname;
        this.contents = contents;
    }
    public BoardComment toEntity(User user, Board board, String contents) {
        return BoardComment.builder()
                .board(board)
                .user(user)
                .contents(contents)
                .build();
    }
}
