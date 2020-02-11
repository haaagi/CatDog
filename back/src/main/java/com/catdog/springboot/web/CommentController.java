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
        System.out.println("request");
        return commentService.findAllAsc(pid);
    }

    @PostMapping("/auth/posts/comment") // 댓글 달기
    public List<Comment> comment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.save(commentRequestDto);
        return commentService.findAllAsc(commentRequestDto.getPid());
    }

//    @DeleteMapping("/auth/posts/comment/delete")
//    public void delete(@PathVariable ) {
//
//    }


}