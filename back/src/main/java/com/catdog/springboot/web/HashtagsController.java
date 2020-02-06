package com.catdog.springboot.web;


import com.catdog.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HashtagsController {

    private final PostsService postsService;

    @PostMapping("/api/posts/hashtags")
    public void applyhashtag(@RequestParam("tags") String tags) {
        postsService.savehashtag(tags);
    }

}
