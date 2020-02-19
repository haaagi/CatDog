package com.catdog.springboot.web;

import com.catdog.springboot.domain.follow.FollowRepository;
import com.catdog.springboot.domain.hashtag.HashtagsRepository;
import com.catdog.springboot.domain.hashtag.Tags;
import com.catdog.springboot.domain.hashtag.TagsRepository;
import com.catdog.springboot.domain.likes.LikesRepository;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.MyPageResponseDto;
import com.catdog.springboot.web.dto.PostsListResponseDto;
import com.catdog.springboot.web.dto.UserPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserPageController {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final FollowRepository followRepository;
    private final TagsRepository tagsRepository;
    private final HashtagsRepository hashtagsRepository;
    private final LikesRepository likesRepository;

    @GetMapping("/auth/myPage/{nickname}") //내 마이페이지 보는 요청
    public MyPageResponseDto MyPage(@PathVariable String nickname) {
        Optional<User> user = userRepository.findByNickname(nickname);
        Long uid = user.get().getUid();
        String pr = user.get().getPr();
        Long post_cnt = postsRepository.cnt_post(uid);
        Long follower_cnt = followRepository.follow_T_Count(uid);
        Long following_cnt = followRepository.follow_F_Count(uid);
        List<Long> follower_list = followRepository.follow_T_list(uid);
        List<Long> following_list = followRepository.follow_F_list(uid);
        List<Posts> posts = postsRepository.postList(uid);
        List<PostsListResponseDto> postsList = new ArrayList<>();
        if(posts != null) {
            for(int i=posts.size()-1; i>=0; i--){
                Long pid = posts.get(i).getPid();
                String postsnickname = posts.get(i).getUser().getNickname();
                String profileimg = posts.get(i).getUser().getImg();
                String img = posts.get(i).getImg();
                String contents = posts.get(i).getContent();
                List<String> hashtags = new ArrayList<>();
                List<Tags> tags = tagsRepository.findAllByPostsPid(pid);
                Long likecount = likesRepository.countLikesByPostsPid(pid);
                String date = posts.get(i).getModifiedDate().toString();
                if(tags != null) {
                    for(int j=0; j<tags.size(); j++){
                        hashtags.add(hashtagsRepository.findByHid(tags.get(j).getHashtags().getHid()).getContent());
                    }
                }
                postsList.add(new PostsListResponseDto(pid, postsnickname,profileimg, img, contents, hashtags, date, likecount));
            }
        }

        // 나를 팔로우 하는 사람
        List<Optional<User>> followerList = new ArrayList<>();
        List<String> followernicknameList = new ArrayList<>();
        // 내가 팔로우 하는 사람
        List<Optional<User>> followingList = new ArrayList<>();
        List<String> followingnicknameList = new ArrayList<>();
        String profileimg = user.get().getImg();
        if(follower_list != null ){
            for(int i = 0; i < follower_list.size(); i++) {
                if(follower_list.get(i) !=null) {
                    Optional<User> followeruser = userRepository.findById(follower_list.get(i));
                    followerList.add(followeruser);
                    followingnicknameList.add(followeruser.get().getNickname());
                }
            }
        }
        if(following_list != null)   {
            for(int i = 0; i < following_list.size(); i++) {
                if(following_list.get(i) !=null){
                    Optional<User> followinguser = userRepository.findById(following_list.get(i));
                    followingList.add(followinguser);
                    followernicknameList.add(followinguser.get().getNickname());
                }
            }
        }

        MyPageResponseDto mypage = new MyPageResponseDto(nickname, profileimg, pr, post_cnt, follower_cnt, following_cnt , postsList,
                followerList, followingList, followernicknameList, followingnicknameList);
        return mypage;
    }


    @GetMapping("/auth/userPage/{mynickname}/{nickname}") //다른사람 페이지 보는 요청
    public UserPageResponseDto UserPage(@PathVariable String mynickname, @PathVariable String nickname) {
        Optional<User> user = userRepository.findByNickname(nickname);
        Optional<User> myobject = userRepository.findByNickname(mynickname);
        boolean isfollow = false;
        Long uid = user.get().getUid();
        String pr = user.get().getPr();
        Long post_cnt = postsRepository.cnt_post(uid);
        Long follower_cnt = followRepository.follow_T_Count(uid);
        Long following_cnt = followRepository.follow_F_Count(uid);
        List<Long> follower_list = followRepository.follow_T_list(uid);
        List<Long> following_list = followRepository.follow_F_list(uid);
        List<Posts> posts = postsRepository.postList(uid);
        List<PostsListResponseDto> postsList = new ArrayList<>();
        if(posts != null) {
            for(int i=posts.size()-1; i>=0; i--){
                Long pid = posts.get(i).getPid();
                String postsnickname = posts.get(i).getUser().getNickname();
                String profileimg = posts.get(i).getUser().getImg();
                String img = posts.get(i).getImg();
                String contents = posts.get(i).getContent();
                List<String> hashtags = new ArrayList<>();
                List<Tags> tags = tagsRepository.findAllByPostsPid(pid);
                Long likecount = likesRepository.countLikesByPostsPid(pid);
                String date = posts.get(i).getModifiedDate().toString();
                if(tags != null) {
                    for(int j=0; j<tags.size(); j++){
                        hashtags.add(hashtagsRepository.findByHid(tags.get(j).getHashtags().getHid()).getContent());
                    }
                }
                postsList.add(new PostsListResponseDto(pid, postsnickname,profileimg, img, contents, hashtags, date, likecount));
            }
        }



        // 나를 팔로우 하는 사람
        List<Optional<User>> followerList = new ArrayList<>();
        List<String> followernicknameList = new ArrayList<>();
        // 내가 팔로우 하는 사람
        List<Optional<User>> followingList = new ArrayList<>();
        List<String> followingnicknameList = new ArrayList<>();
        String profileimg = user.get().getImg();
        if(follower_list != null ){
            for(int i = 0; i < follower_list.size(); i++) {
                if(follower_list.get(i) !=null) {
                    Optional<User> followeruser = userRepository.findById(follower_list.get(i));
                    followerList.add(followeruser);
                    followingnicknameList.add(followeruser.get().getNickname());
                }
            }
        }
        if(following_list != null)   {
            for(int i = 0; i < following_list.size(); i++) {
                if(following_list.get(i) !=null){
                    Optional<User> followinguser = userRepository.findById(following_list.get(i));
                    followingList.add(followinguser);
                    followernicknameList.add(followinguser.get().getNickname());
                }
            }
        }
        UserPageResponseDto userpage = new UserPageResponseDto(nickname, profileimg, pr, post_cnt, follower_cnt, following_cnt , postsList,
                followerList, followingList, followernicknameList, followingnicknameList, isfollow);
        return userpage;
    }
}
