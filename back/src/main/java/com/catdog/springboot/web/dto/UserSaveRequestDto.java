package com.catdog.springboot.web.dto;


import com.catdog.springboot.domain.user.Role;
import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String birthday;
    private String picture;
    private String role;

    @Builder
    public UserSaveRequestDto(String email,String password,
                              String name, String nickname, String birthday,
                              String picture, String role) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
        this.picture = picture;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .birthday(birthday)
                .picture(picture)
                .role(Role.USER)
                .build();
    }
}
