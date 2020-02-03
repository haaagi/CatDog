package com.catdog.springboot.web;


import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.service.JwtService;
import com.catdog.springboot.service.UserService;
import com.catdog.springboot.web.dto.PostsSaveRequestDto;
import com.catdog.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;


    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/v1/user")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<Map<String, Object>> signin (@RequestBody User user, HttpServletResponse res) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            User loginUser = userService.signin(user.getEmail(), user.getPassword());
            String token = jwtService.create(loginUser);
            System.out.println(token);
            res.setHeader("jwt-auth-token", token);
            resultMap.put("status", true);
            resultMap.put("data", loginUser);
            status = HttpStatus.ACCEPTED;
            System.out.println(loginUser.getEmail() + " " + loginUser.getPassword());
        } catch (RuntimeException e) {
//            log.error("로그인 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    @PostMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody User user) {
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

}
