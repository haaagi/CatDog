package com.catdog.springboot.web;

import com.catdog.springboot.service.PostsService;
import com.catdog.springboot.service.TestService;
import com.catdog.springboot.web.dto.PostsSaveRequestDto;
import com.catdog.springboot.web.dto.TestSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @PostMapping("/api/v1/test")
    public Long save(@RequestBody TestSaveRequestDto requestDto) {
        return testService.save(requestDto);
    }
}
