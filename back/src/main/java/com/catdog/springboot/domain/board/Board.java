package com.catdog.springboot.domain.board;

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
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    @Column
    private Long type;

    @Column
    private String title;

    @Column
    private String contents;

    @JsonBackReference
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uid")
    private User user;

    @Builder
    public Board(User user, String title, String contents, Long type) {
        this.user = user;
        this.type = type;
        this.title = title;
        this.contents = contents;
    }

    public void update( String contents, String title) {
        this.contents = contents;
        this.title = title;
    }

}