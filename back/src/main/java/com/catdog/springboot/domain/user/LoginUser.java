package com.catdog.springboot.domain.user;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginUser {
    private String email;
    private String password;

    @Builder
    public LoginUser(String email, String password) {

        this.email = email;
        this.password = password;
    }

}
