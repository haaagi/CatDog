package com.catdog.springboot.service;


import com.catdog.springboot.domain.user.LoginUser;
import com.catdog.springboot.domain.user.Role;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.UserSaveRequestDto;
import com.catdog.springboot.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private static final Role USER = Role.USER;
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getUid();
    }

    @Transactional
    public LoginUser signin(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if(user != null && password.equals(user.getPassword())) {
            return new LoginUser(email, password, user.getNickname());
        }else if(user != null && !password.equals(user.getPassword())){
            throw new RuntimeException("비밀번호가 틀립니다");
        }else {
            throw new RuntimeException("그런 사람은 없어요~");
        }
    }
    public String getserverInfo(){
        return "cat_dog";
    }

    @Transactional
    public User update(String nickname, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findByNickname(nickname).orElse(null);
        user.update(userUpdateRequestDto);
        return user;
    }

    @Transactional
    public Long delete(String nickname) {
        User user = userRepository.findByNickname(nickname).orElse(null);
        System.out.println(user.getName());
        userRepository.delete(user);
        return 1L;
    }
}
