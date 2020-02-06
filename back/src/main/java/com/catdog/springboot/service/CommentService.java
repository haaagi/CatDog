package com.catdog.springboot.service;

import com.catdog.springboot.domain.comment.Comment;
import com.catdog.springboot.domain.comment.CommentRepository;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final CommentRepository commentRepository;
    @Transactional
    public List<Comment> findAllAsc(Long pid) {
        System.out.println("service");
        return commentRepository.findAllByPostsPid(pid);
    }
}