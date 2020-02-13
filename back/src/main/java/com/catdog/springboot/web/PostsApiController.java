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

    @GetMapping("/api/posts/postdetail/{mynickname}/{pid}")
    public PostsResponseDto detail(@PathVariable String mynickname, @PathVariable Long pid) {
        return postsService.detail(mynickname, pid);
    }

    @PostMapping("/auth/posts/posting") // 게시글 업로드
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

//    @GetMapping("/api/posts/{nickname}") //해당 닉네임 유저 페이지
//    public List<PostsListResponseDto> findByNickname(@PathVariable String nickname) {
//        return postsService.findByNickname(nickname);
//    }

    @PutMapping("/auth/posts/update/{pid}") // 게시글 수정
    public void update(@PathVariable Long pid, @RequestBody PostsUpdateRequestDto requestDto) {
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
}
