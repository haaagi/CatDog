package com.catdog.springboot.domain.hashtag;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Hashtags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hid;

    @Column
    private String content;

    @Builder
    public Hashtags(String content) {
        this.content = content;
    }
}
