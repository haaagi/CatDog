package com.catdog.springboot.web;


import com.catdog.springboot.domain.hashtag.HashtagsRepository;
import com.catdog.springboot.domain.hashtag.Tags;
import com.catdog.springboot.domain.hashtag.TagsRepository;
import com.catdog.springboot.domain.likes.LikesRepository;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class HashtagsController {

    private final HashtagsRepository hashtagsRepository;
    private final TagsRepository tagsRepository;
    private final PostsRepository postsRepository;
    private final LikesRepository likesRepository;

    @GetMapping("/hashtag/{keyword}")
    public List<PostsListResponseDto> findhashtag(@PathVariable String keyword) {
        Long hid = hashtagsRepository.findhid(keyword);
        List<Long> pids = tagsRepository.findpids(hid);
        List<PostsListResponseDto> posts = new ArrayList<>();
        for(int i = pids.size()-1; i >= 0; i--) {
            Long pid = pids.get(i);
            Posts post1 = postsRepository.findByPid(pid);
            String followernickname = post1.getUser().getNickname();
            String profileimg = post1.getUser().getImg();
            String img = post1.getImg();
            String contents = post1.getContent();
            List<String> hashtags = new ArrayList<>();
            List<Tags> tags = tagsRepository.findAllByPostsPid(pid);
            Long likecount = likesRepository.countLikesByPostsPid(pid);
            String date = post1.getModifiedDate().toString();
            if(tags != null) {
                for(int j=0; j<tags.size(); j++){
                    hashtags.add(hashtagsRepository.findByHid(tags.get(j).getHashtags().getHid()).getContent());
                }
            }
            posts.add(new PostsListResponseDto(pid, followernickname,profileimg, img, contents, hashtags, date, likecount));
        }
        return posts;
    }

}
