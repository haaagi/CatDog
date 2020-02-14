package com.catdog.springboot.domain.user;

import com.catdog.springboot.domain.BaseTimeEntity;
import com.catdog.springboot.web.dto.UserUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    private String name;

    @Column
    private String nickname;

    @Column
    private String pr;

    @Column
    private String birthday;

    @Column
    private String img;

    @Column
    private String likepet;

    @Column
    private String mypet;

    @Column
    private String phonenumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;



    @Builder
    public User(String email, String password,String name, String nickname,String birthday,String likepet,String mypet, String phonenumber, Role role) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
        this.likepet = likepet;
        this.mypet = mypet;
        this.phonenumber = phonenumber;
        this.role = role;
    }
    public User authUpdate(String img){
        this.img = img;
        return this;
    }

    public User update(UserUpdateRequestDto userUpdateRequestDto) {
        if(userUpdateRequestDto.getPassword() != null) this.password = userUpdateRequestDto.getPassword();
        if(userUpdateRequestDto.getNickname() != null) this.nickname = userUpdateRequestDto.getNickname();
        if(userUpdateRequestDto.getPr() != null) this.pr = userUpdateRequestDto.getPr();
        if(userUpdateRequestDto.getBirthday() != null) this.birthday = userUpdateRequestDto.getBirthday();
        if(userUpdateRequestDto.getImg() != null) this.img = userUpdateRequestDto.getImg();
        if(userUpdateRequestDto.getLikePet() != null) this.likepet = userUpdateRequestDto.getLikePet();
        if(userUpdateRequestDto.getMyPet() != null) this.mypet = userUpdateRequestDto.getMyPet();
        if(userUpdateRequestDto.getPhoneNumber() != null) this.phonenumber = userUpdateRequestDto.getPhoneNumber();
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
