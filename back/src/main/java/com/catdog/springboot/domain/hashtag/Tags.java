package com.catdog.springboot.domain.hashtag;

import com.catdog.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "hid")
    private Hashtags hashtags;

    @Builder
    public Tags(Posts posts, Hashtags hashtags) {
        this.posts = posts;
        this.hashtags = hashtags;
    }
}
