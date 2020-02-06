package com.catdog.springboot.web.dto;


//import com.catdog.springboot.domain.user.Role;

import com.catdog.springboot.domain.user.Role;
import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String birthday;
    private List<String> myPet;
    private List<String> likePet;
    private String phoneNumber;
    private Role role;

    @Builder
    public UserSaveRequestDto(String email, String password,String name, String nickname,String birthday
            ,List likepet,List mypet, String phonenumber, Role role) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
        this.likePet = likepet;
        this.myPet = mypet;
        this.phoneNumber = phonenumber;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .birthday(birthday)
                .mypet(ListToString(myPet))
                .likepet(ListToString(likePet))
                .phonenumber(phoneNumber)
                .role(Role.USER)
                .build();
    }


    public String ListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if(list != null) {
            for(int i=0; i<list.size(); i++){
                sb.append(list.get(i));
                if(i != (list.size() - 1)) {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
        else {
            return "";
        }
    }
}
