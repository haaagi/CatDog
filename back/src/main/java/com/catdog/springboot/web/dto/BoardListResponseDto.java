package com.catdog.springboot.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardListResponseDto {

    private Long bid;
    private Long type;
    private String title;
    private String contents;
    private String nickname;
    private String modifiedDate;

    @Builder
    public BoardListResponseDto(Long bid, Long type, String title, String contents, String nickname, String modifiedDate ) {
        this.bid = bid;
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.modifiedDate = modifiedDate;
    }

}
