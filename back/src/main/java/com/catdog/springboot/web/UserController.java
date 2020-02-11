package com.catdog.springboot.web;


import com.catdog.springboot.domain.hashtag.HashtagsRepository;
import com.catdog.springboot.domain.user.LoginUser;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.service.JwtService;
import com.catdog.springboot.service.UserService;
import com.catdog.springboot.web.dto.JwtResponseDto;
import com.catdog.springboot.web.dto.SearchRequestDto;
import com.catdog.springboot.web.dto.UserSaveRequestDto;
import javassist.compiler.ast.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final HashtagsRepository hashtagsRepository;
    //private final FileUploadService uploadService;

    @PostMapping("/api/user/signup") // 회원가입
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @PostMapping("/api/user/signin") //로그인
    public ResponseEntity<?> signin (@RequestBody LoginUser user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String token = "";
        String email = "";
        String nickname = "";
        try {
            LoginUser loginUser = userService.signin(user.getEmail(), user.getPassword());
            token = jwtService.create(loginUser);
            email = loginUser.getEmail();
            nickname = loginUser.getNickname();
            System.out.println(token);
            status = HttpStatus.ACCEPTED;
            System.out.println(loginUser.getEmail() + " " + loginUser.getPassword());
        } catch (RuntimeException e) {
//            log.error("로그인 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.ok(new JwtResponseDto(token, email, nickname));
    }

    @PostMapping("/auth/user/info") //유저정보
    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody LoginUser user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            String info = userService.getserverInfo();
            resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
            resultMap.put("status", true);
            resultMap.put("info", info);
            resultMap.put("request_body" , user);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
//            log.error("정보조회 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/auth/user/search/{keyword}")
    public SearchRequestDto Search(@PathVariable String keyword) {

        List<Optional<User>> userList = userRepository.searchuser(keyword);
        List<String> tagList = hashtagsRepository.searchtag(keyword);
        SearchRequestDto searchRequestDto = new SearchRequestDto(userList, tagList);
        return searchRequestDto;
    }

}