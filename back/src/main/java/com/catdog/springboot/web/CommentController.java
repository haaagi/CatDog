package com.catdog.springboot.web;


import com.catdog.springboot.domain.comment.Comment;
import com.catdog.springboot.service.CommentService;
import com.catdog.springboot.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/auth/posts/comment/{pid}") // 해당 포스트에 달려있는 댓글 보여주기
    public List<Comment> commentlist(@PathVariable Long pid) {
        return commentService.findAllAsc(pid);
    }

    @PostMapping("/auth/posts/comment") // 댓글 달기
    public List<Comment> comment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.save(commentRequestDto);
        return commentService.findAllAsc(commentRequestDto.getPid());
    }

    @DeleteMapping("/auth/posts/comment/delete/{pid}/{cid}") //포스트에 달린 댓글삭제
    public List<Comment> delete(@PathVariable Long pid, @PathVariable Long cid) {
        commentService.delete(cid);
        return commentService.findAllAsc(pid);
    }



}