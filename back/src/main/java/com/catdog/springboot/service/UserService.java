package com.catdog.springboot.service;


import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getUid();
    }

    public User signin(String email, String password) {
        if(email.equals("abc@def.net") && password.equals("1234")) {
            return new User(email, password, null, null, null, null, null);
        }else {
            throw new RuntimeException("그런 사람은 없어요~");
        }
    }

    public String getserverInfo(){
        return "cat_dog";
    }
}
