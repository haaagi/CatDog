package com.catdog.springboot.web;

import com.catdog.springboot.service.PostsService;
import com.catdog.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @GetMapping("/api/posts/list") //포스트 리스트 가져오기 (메인화면에 사용)
    public List<PostsListResponseDto> list() {
        return postsService.findAll();
    }

    @GetMapping("/api/posts/list/{nickname}") //포스트 리스트 가져오기 (메인화면에 사용)
    public List<PostsListResponseDto> list(@PathVariable String nickname) {
        return postsService.findAllByNickname(nickname);
    }

    @GetMapping("/api/posts/search/{hashtag}") //검색한 리스트에서 선택한 해시태그달린 게시물 전체 검색
    public List<PostsListResponseDto> tagsearch(@PathVariable String hashtag) {
        return postsService.findAllByHashtag(hashtag);
    }

    @GetMapping("/api/posts/postdetail/{mynickname}/{pid}") // 해당 포스트 보기
    public PostsResponseDto detail(@PathVariable String mynickname, @PathVariable Long pid) {
        return postsService.detail(mynickname, pid);
    }

    @GetMapping("/api/posts/postdetail/{pid}")
    public PostsResponseDto2 detail (@PathVariable Long pid){
        return postsService.detail2(pid);
    }


    @PostMapping("/auth/posts/posting") // 게시글 업로드
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/auth/posts/update/{pid}") // 게시글 수정
    public void update(@PathVariable Long pid, @RequestBody PostsUpdateRequestDto requestDto) {
        System.out.println(requestDto.getContent());
        postsService.update(pid, requestDto);
    }

    @DeleteMapping("/auth/posts/delete/{pid}") //게시글 삭제
    public void delete(@PathVariable Long pid) {
        postsService.delete(pid);
    }

    @PostMapping("/auth/posts/likesup")  //좋아요 누를때
    public boolean likesup(@RequestBody PostsLikesupRequestDto likesupRequestDto) {
        return postsService.likesup(likesupRequestDto);
    }

    @GetMapping("/api/posts/likeslist/{pid}") // 해당 Post 좋아요 누른 사람들 목록
    public List<String> likeslist(@PathVariable Long pid) {
        return postsService.likeslist(pid);
    }


}
