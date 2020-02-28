package com.catdog.springboot.web;

import com.catdog.springboot.domain.hashtag.HashtagsRepository;
import com.catdog.springboot.domain.hashtag.TagsRepository;
import com.catdog.springboot.domain.user.LoginUser;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.service.JwtService;
import com.catdog.springboot.service.UserService;
import com.catdog.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final HashtagsRepository hashtagsRepository;
    private final TagsRepository tagsRepository;
    @PostMapping("/api/user/signup") // 회원가입
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        User user = userRepository.findByNickname(requestDto.getNickname()).orElse(null);
        if(user != null) {return -1L;}
        return userService.save(requestDto);
    }

    @PostMapping("/api/user/signin") //로그인
    public ResponseEntity<?> signin(@RequestBody LoginUser user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String token = "";
        String email = "";
        String nickname = "";
        Optional<User> logincheckuser = userRepository.findByEmail(user.getEmail());
        if(logincheckuser == null) {
            token = "no user";
            return ResponseEntity.ok(new JwtResponseDto(token, email, nickname));
        }else if( logincheckuser != null && !(logincheckuser.get().getPassword().equals(user.getPassword()))){
            token ="no password";
            return ResponseEntity.ok(new JwtResponseDto(token, email, nickname));
        }else {
            try {
                LoginUser loginUser = userService.signin(user.getEmail(), user.getPassword());
                token = jwtService.create(loginUser);
                email = loginUser.getEmail();
                nickname = loginUser.getNickname();
                System.out.println(token);
                status = HttpStatus.ACCEPTED;
                System.out.println(loginUser.getEmail() + " " + loginUser.getPassword());
            } catch (RuntimeException e) {
//              log.error("로그인 실패", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return ResponseEntity.ok(new JwtResponseDto(token, email, nickname));
        }

    }

    @PutMapping("/auth/user/update/{nickname}")
    public User update(@PathVariable String nickname, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.update(nickname, userUpdateRequestDto);
    }

    @DeleteMapping("/auth/user/delete/{nickname}")
    public Long delete(@PathVariable String nickname) {
        return userService.delete(nickname);
    }

    @PostMapping("/test/auth/user/info") //유저정보
    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody LoginUser user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            String info = userService.getserverInfo();
            resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
            resultMap.put("status", true);
            resultMap.put("info", info);
            resultMap.put("request_body", user);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
//            log.error("정보조회 실패", e);
            resultMap.put("message", "유효한 토큰이 아닙니다");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/auth/user/search/{keyword}")
    public SearchRequestDto Search(@PathVariable String keyword) {

        List<Optional<User>> userList = userRepository.searchuser(keyword);
        List<Long> tagList = hashtagsRepository.searchtag(keyword);

        List<SearchHashTagDto> list = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            String content = hashtagsRepository.findContent(tagList.get(i));
            Long content_cnt = tagsRepository.content_cnt(tagList.get(i));
            if(content_cnt != 0 ) {
                list.add(new SearchHashTagDto(content, content_cnt));
            }
        }

        SearchRequestDto searchRequestDto = new SearchRequestDto(userList, list);
        return searchRequestDto;
    }

}