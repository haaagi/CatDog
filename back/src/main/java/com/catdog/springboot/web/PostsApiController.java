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

    @GetMapping("/api/posts/postdetail/{pid}")
    public PostsResponseDto detail(@PathVariable Long pid) {
        return postsService.detail(pid);
    }

    @PostMapping("/auth/posts/posting") // 게시글 업로드
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

//    @GetMapping("/api/posts/{nickname}") //해당 닉네임 유저 페이지
//    public List<PostsListResponseDto> findByNickname(@PathVariable String nickname) {
//        return postsService.findByNickname(nickname);
//    }

    @PutMapping("/auth/posts/update/{id}") // 게시글 수정 (아직 구현 안됨)
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/auth/posts/delete/{id}") //게시글 삭제 (아직 구현 안됨)
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @PostMapping("/auth/posts/likesup")  //좋아요 누를때
    public Long likesup(@RequestBody PostsLikesupRequestDto likesupRequestDto) {
        return postsService.likesup(likesupRequestDto);
    }
}
