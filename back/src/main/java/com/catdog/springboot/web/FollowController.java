package com.catdog.springboot.web;


import com.catdog.springboot.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

    @GetMapping("/auth/follow/save/{follower}/{following}") //팔로우 요청하는사람(follower) - 팔로우 요청받는사람  (following)
    public Long Follow(@PathVariable String follower, @PathVariable String following) {
        return followService.follow(follower, following);
    }
}
