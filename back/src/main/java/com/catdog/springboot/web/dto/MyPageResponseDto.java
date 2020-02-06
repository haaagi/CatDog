package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.service.PostsService;
import com.catdog.springboot.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
public class MyPageResponseDto {

    @Autowired
    PostsRepository postsRepository;
    private Long uid; // User id

    private Long post_cnt; // 내 게시물 수

    private Long follow_from; // 나를 팔로우 한사람

    private Long follow_to;   // 내가 팔로우 한사람

    private List<Posts> postsList; // 게시물 리스트

    public MyPageResponseDto(Long uid, Long post_cnt) {
        this.uid = uid;
        this.post_cnt =  post_cnt;
    }
}
