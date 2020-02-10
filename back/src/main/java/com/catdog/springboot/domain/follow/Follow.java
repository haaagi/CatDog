package com.catdog.springboot.domain.follow;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    @Column
    private Long Follow_request; // 신청한 사람

    @Column
    private Long Follow_response; // 신청 받는 사람

}
