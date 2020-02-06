package com.catdog.springboot.web;


import com.catdog.springboot.domain.follow.Follow;
import com.catdog.springboot.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

//    @PostMapping("/follow/save")
//    private void save() {
//        return followService.
//    }

//    @GetMapping
//    private Long FollowCount(@PathVariable Long uid) {
//        return followService.FollowCount_Cal(uid);
//    }
}
