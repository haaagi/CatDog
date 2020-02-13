package com.catdog.springboot.web;


import com.catdog.springboot.domain.hashtag.HashtagsRepository;
import com.catdog.springboot.domain.hashtag.TagsRepository;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class HashtagsController {

    private final HashtagsRepository hashtagsRepository;
    private final TagsRepository tagsRepository;
    private final PostsRepository postsRepository;

    @GetMapping("/hashtag/{keyword}")
    public List<Posts> findhashtag(@PathVariable String keyword) {
        Long hid = hashtagsRepository.findhid(keyword);
        List<Long> pids = tagsRepository.findpids(hid);
        List<Posts> posts = new ArrayList<>();
        for(int i = 0; i < pids.size(); i++) {
            posts.add(postsRepository.findAllByPid(pids.get(i)));
        }
        return posts;
    }

}
