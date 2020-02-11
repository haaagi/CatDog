package com.catdog.springboot.domain.follow;

import com.catdog.springboot.domain.user.User;
import lombok.Builder;
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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Followrequest_uid")
    private User follower; // 신청한 사람

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Followresponse_uid")
    private User following; // 신청 받는 사람

    @Builder
    public Follow(User follower, User following){
        this.follower = follower;
        this.following = following;
    }

}
