package com.catdog.springboot.domain.posts;

import com.catdog.springboot.domain.BaseTimeEntity;
import com.catdog.springboot.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @JsonBackReference
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uid")
    private User user;

    @Builder
    public Posts(User user, String img, String content) {
        this.user = user;
        this.img = img;
        this.content = content;
    }

    public void update( String content) {
        this.content = content;
    }

}