package com.catdog.springboot.domain.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500 , nullable = false)
    private String title;

    @Builder
    public Test(String title) {
        this.title = title;
    }
}
