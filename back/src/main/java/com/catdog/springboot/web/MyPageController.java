package com.catdog.springboot.web;

import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.MyPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    @GetMapping("/MyPage/{id}")
    public MyPageResponseDto MyPage(@PathVariable String id) {
        Optional<User> user = userRepository.findByEmail(id);
        Long uid = user.get().getUid();
        Long post_cnt = postsRepository.cnt_post(uid);

        MyPageResponseDto mypage = new MyPageResponseDto(uid, post_cnt);
        return mypage;
    }
}
