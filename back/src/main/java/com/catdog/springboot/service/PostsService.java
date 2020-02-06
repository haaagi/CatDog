package com.catdog.springboot.service;

import com.catdog.springboot.domain.hashtag.Hashtags;
import com.catdog.springboot.domain.hashtag.HashtagsRepository;
import com.catdog.springboot.domain.likes.LikesRepository;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
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
    private final HashtagsRepository hashtagsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) { // 게시글 게시 요청
        User user = userRepository.findByEmail(requestDto.getEmail()).orElse(null);
        postsRepository.save(requestDto.toEntity(user));
        List<String> taglist = requestDto.getHashtags();
        if(taglist.size()>0) {
            for(int i=0; i<taglist.size(); i++){
                if(hashtagsRepository.findByContent(taglist.get(i)) == null) {
                    hashtagsRepository.save(Hashtags.builder().content(taglist.get(i)).build());
                }
            }
        }
        return user.getUid();
    }

    @Transactional
    public Long update(Long pid, PostsUpdateRequestDto requestDto) { //게시글 수정 요청
        Posts posts = postsRepository.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + pid));

     //   posts.update(requestDto.getImg(), requestDto.getContent(), requestDto.getHashtags());

        return pid;
    }

    public PostsResponseDto findById(Long pid) {

        Posts entity = postsRepository.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + pid));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAll() {
        return postsRepository.findAllByOrderByCreatedDateDesc().stream()
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

    @Transactional
    public void savehashtag(String tags) {
        Hashtags hashtags = hashtagsRepository.findByContent(tags).orElse(null);
        if(hashtags == null) {
            hashtagsRepository.save(hashtags.builder().content(tags).build());
        }else {

        }

    }
}