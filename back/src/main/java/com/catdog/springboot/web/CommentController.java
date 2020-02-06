package com.catdog.springboot.web;


import com.catdog.springboot.domain.comment.Comment;
import com.catdog.springboot.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/comment/list/{pid}")
    private List<Comment> commentlist(@PathVariable Long pid) {
        System.out.println("request");
        return commentService.findAllAsc(pid);
    }
}