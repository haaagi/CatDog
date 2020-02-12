package com.catdog.springboot.service;

import com.catdog.springboot.domain.comment.Comment;
import com.catdog.springboot.domain.comment.CommentRepository;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.CommentRequestDto;
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
        return commentRepository.findAllByPostsPid(pid);
    }

    @Transactional
    public Long save(CommentRequestDto requestDto) {
        User user = userRepository.findByNickname(requestDto.getNickname()).orElse(null);
        Posts post = postsRepository.findByPid(requestDto.getPid());
        return commentRepository.save(requestDto.toEntity(user, post, requestDto.getContents())).getCid();
    }

    @Transactional
    public void delete(Long cid) {
        Comment comment = commentRepository.findById(cid).orElse(null);
        commentRepository.delete(comment);
    }

}