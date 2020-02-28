package com.catdog.springboot.domain.comment;

import com.catdog.springboot.domain.BaseTimeEntity;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid; // Comment ID

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uid") // 유저 번호
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pid") // 게시글 번호
    private Posts posts;

    @Column
    private String content; // 글 내용


    @Builder
    public Comment(User user, Posts posts , String content ) {
        this.user = user;
        this.posts = posts;
        this.content = content;

    }

    public void update(String content) {
        this.content = content;
    }
}