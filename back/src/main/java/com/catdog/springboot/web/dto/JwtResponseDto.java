package com.catdog.springboot.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {
    private String accessToken;
    private String tokenType ="Bearer";
    private String email;
    private String nickname;
    public JwtResponseDto(String accessToken, String email, String nickname) {
        this.accessToken = accessToken;
        this.email = email;
        this.nickname = nickname;
    }

}
