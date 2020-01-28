package com.catdog.springboot.service;

import com.catdog.springboot.domain.test.TestRepository;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.TestSaveRequestDto;
import com.catdog.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;
    @Transactional
    public Long save(TestSaveRequestDto requestDto) {
        return testRepository.save(requestDto.toEntity()).getId();
    }
}
