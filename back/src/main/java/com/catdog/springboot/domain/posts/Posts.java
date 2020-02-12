package com.catdog.springboot.domain.posts;

import com.catdog.springboot.domain.BaseTimeEntity;
import com.catdog.springboot.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    
    @Column
    private String img;


    @Column
    private String content;

    @ManyToOne(cascade=CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "uid")
    private User user;

    @Builder
    public Posts(User user, String img, String content) {
        this.user = user;
        this.img = img;
        this.content = content;
    }

    public void update(String img, String content) {
        this.img = img;
        this.content = content;
    }

}