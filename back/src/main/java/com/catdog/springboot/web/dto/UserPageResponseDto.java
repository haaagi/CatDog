package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class UserPageResponseDto {

    private String nickname; // User id

    private String profileimg; // User img

    private String pr;  // 자기소개

    private Long post_cnt; // 내 게시물 수

    private Long follower_cnt; // 나를 팔로우 한사람

    private Long following_cnt;   // 내가 팔로우 한사람

    private List<Posts> postsList; // 게시물 리스트

    private List<Optional<User>> followerList;

    private List<Optional<User>> followingList;
    private boolean isfollow;

    public UserPageResponseDto(String nickname, String profileimg , String pr, Long post_cnt , Long follower_cnt, Long following_cnt,
                               List<Posts> postsList, List<Optional<User>> followerList, List<Optional<User>> followingList, boolean isfollow) {
        this.nickname = nickname;
        this.profileimg = profileimg;
        this.pr = pr;
        this.post_cnt =  post_cnt;
        this.follower_cnt = follower_cnt;
        this.following_cnt = following_cnt;
        this.postsList = postsList;
        this.followerList = followerList;
        this.followingList = followingList;
        this.isfollow = isfollow;
    }
}
