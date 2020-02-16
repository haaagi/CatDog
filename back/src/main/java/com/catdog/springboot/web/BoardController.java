package com.catdog.springboot.web;


import com.catdog.springboot.domain.board.BoardComment;
import com.catdog.springboot.service.BoardService;
import com.catdog.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/board/list") //게시글 리스트 가져오기
    public List<BoardListResponseDto> list() {
        return boardService.findAll();
    }


    @GetMapping("/api/board/boarddetail/{bid}") // 해당 게시글 보기
    public BoardResponseDto detail(@PathVariable Long bid) {
        return boardService.detail(bid);
    }


    @PostMapping("/auth/board/upload") // 게시글 업로드
    public Long save(@RequestBody BoardSaveRequestDto requestDto) {
        return boardService.save(requestDto);
    }


    @PutMapping("/auth/board/update/{bid}") // 게시글 수정
    public void update(@PathVariable Long bid, @RequestBody BoardUpdateRequestDto requestDto) {
        boardService.update(bid, requestDto);
    }

    @DeleteMapping("/auth/board/delete/{bid}") //게시글 삭제
    public void delete(@PathVariable Long bid) {
        boardService.delete(bid);
    }


    @PostMapping("/auth/board/comment") // 게시글에 댓글 달기
    public List<BoardComment> comment(@RequestBody BoardCommentRequestDto commentRequestDto) {
        return boardService.comment(commentRequestDto);
    }

    @DeleteMapping("/auth/posts/comment/delete/{bid}/{bcid}") // 게시글 댓글 삭제
        public List<BoardComment> commentdelete(@PathVariable Long bid, @PathVariable Long bcid) {
        return boardService.commentdelete(bid , bcid);
    }


}
