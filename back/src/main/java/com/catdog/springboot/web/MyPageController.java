package com.catdog.springboot.web;

import com.catdog.springboot.domain.follow.FollowRepository;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.MyPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final FollowRepository followRepository;

    @GetMapping("/MyPage/{id}")
    public MyPageResponseDto MyPage(@PathVariable String id) {
        Optional<User> user = userRepository.findByEmail(id);
        Long uid = user.get().getUid();
        Long post_cnt = postsRepository.cnt_post(uid);
        Long follow_from = followRepository.follow_F_Count(uid);
        Long follow_to = followRepository.follow_T_Count(uid);
        List<Posts> postsList = postsRepository.postList(uid);
        List<Long> follow_list = followRepository.follow_F_list(uid);
        List<Long> folloing_list = followRepository.follow_T_list(uid);
        // 나를 팔로우 하는 사람
        List<Optional<User>> follower = new ArrayList<>();
        // 내가 팔로우 하는 사람
        List<Optional<User>> folloing = new ArrayList<>();
        String img = user.get().getImg();
        for(int i = 0; i < follow_list.size(); i++) {
            follower.add(userRepository.findById(follow_list.get(i)));
        }

        for(int i = 0; i < folloing_list.size(); i++) {
            folloing.add(userRepository.findById(folloing_list.get(i)));
        }

        MyPageResponseDto mypage = new MyPageResponseDto(uid, img, post_cnt, follow_from, follow_to , postsList, follower, folloing);

        return mypage;
    }
}
