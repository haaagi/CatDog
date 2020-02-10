package com.catdog.springboot.domain.user;

import com.catdog.springboot.domain.BaseTimeEntity;
import com.catdog.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy="user")
    private List<Posts> posts = new ArrayList<Posts>();



    @Builder
    public User(String email, String password,String name, String nickname,String birthday,String img,String likepet,String mypet, String phonenumber, Role role) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
        this.img = img;
        this.likepet = likepet;
        this.mypet = mypet;
        this.phonenumber = phonenumber;
        this.role = role;
    }

    public User update( String img) {
        this.img = img;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}
