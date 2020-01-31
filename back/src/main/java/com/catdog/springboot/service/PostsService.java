package com.catdog.springboot.service;

import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.likes.LikesRepository;
import com.catdog.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getAuthor()).orElse(null);
        return postsRepository.save(requestDto.toEntity(user)).getPid();
    }

    @Transactional
    public Long update(Long pid, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + pid));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return pid;
    }

    public PostsResponseDto findById(Long pid) {
        Posts entity = postsRepository.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + pid));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllAsc() {
        return postsRepository.findAllAsc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional
    public List<Object[]> likescount() {
        List<Object[]> postsLikesResponseDtos = likesRepository.likescount();
        return postsLikesResponseDtos;
    }

    @Transactional
    public Long likesup(PostsLikesupRequestDto likesupRequestDto) {
        User user = userRepository.findByEmail(likesupRequestDto.getEmail()).orElse(null);
        Posts posts = postsRepository.findById(likesupRequestDto.getPid()).orElse(null);
        return likesRepository.save(likesupRequestDto.toEntity(user, posts)).getLid();
    }



}