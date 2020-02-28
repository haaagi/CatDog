package com.catdog.springboot.domain.board;

import com.catdog.springboot.domain.BaseTimeEntity;
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
public class BoardComment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bcid; // Comment ID

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uid") // 유저 번호
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "bid") // 게시글 번호
    private Board board;

    @Column
    private String contents; // 글 내용


    @Builder
    public BoardComment(User user, Board board , String contents ) {
        this.user = user;
        this.board = board;
        this.contents = contents;

    }

}
