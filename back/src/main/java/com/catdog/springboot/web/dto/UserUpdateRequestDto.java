package com.catdog.springboot.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String password;
    private String name;
    private String nickname;
    private String pr;
    private String birthday;
    private String img;
    private String myPet;
    private String likePet;
    private String phoneNumber;

    @Builder
    public UserUpdateRequestDto(String password,String name, String nickname, String pr, String birthday, String img
            ,List likepet,List mypet, String phonenumber) {

        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.pr = pr;
        this.birthday = birthday;
        this.img = img;
        this.likePet = ListToString(likepet);
        this.myPet = ListToString(mypet);
        this.phoneNumber = phonenumber;

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
