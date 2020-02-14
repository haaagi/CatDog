package com.catdog.springboot.web;


import com.catdog.springboot.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

    @GetMapping("/auth/follow/save/{follower}/{following}")
    private void save(@PathVariable String follower, @PathVariable String following) {
        followService.save(follower, following);
    }
}
