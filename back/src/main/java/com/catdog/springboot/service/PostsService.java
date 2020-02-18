package com.catdog.springboot.service;

import com.catdog.springboot.domain.comment.Comment;
import com.catdog.springboot.domain.comment.CommentRepository;
import com.catdog.springboot.domain.follow.FollowRepository;
import com.catdog.springboot.domain.hashtag.Hashtags;
import com.catdog.springboot.domain.hashtag.HashtagsRepository;
import com.catdog.springboot.domain.hashtag.Tags;
import com.catdog.springboot.domain.hashtag.TagsRepository;
import com.catdog.springboot.domain.likes.Likes;
import com.catdog.springboot.domain.likes.LikesRepository;
import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.posts.PostsRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final HashtagsRepository hashtagsRepository;
    private final TagsRepository tagsRepository;
    private final CommentRepository commentRepository;
    private final FollowRepository followRepository;
    @Transactional
    public List<PostsListResponseDto> findAll() {
        List<PostsListResponseDto> postlist = new ArrayList<PostsListResponseDto>();
        List<Posts> posts = postsRepository.findAllByOrderByCreatedDateDesc();
        if(posts != null) {
            for(int i=0; i<posts.size(); i++){
                Long pid = posts.get(i).getPid();
                String nickname = posts.get(i).getUser().getNickname();
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
                postlist.add(new PostsListResponseDto(pid, nickname, profileimg, img, contents, hashtags, date, likecount));
            }
        }
        return postlist; //이거 잘 리턴되는지 봐야함
    }

    @Transactional
    public List<PostsListResponseDto> findAll(String nickname) {
        List<PostsListResponseDto> postlist = new ArrayList<PostsListResponseDto>();
        Optional<User> user = userRepository.findByNickname(nickname);
        Long uid = user.get().getUid();
        List<Posts> posts = postsRepository.followingpostList(uid);
        if(posts != null) {
            for(int i= posts.size()-1; i>=0; i--){
                Long pid = posts.get(i).getPid();
                String followernickname = posts.get(i).getUser().getNickname();
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
                postlist.add(new PostsListResponseDto(pid, followernickname,profileimg, img, contents, hashtags, date, likecount));
            }
        }
        return postlist; //이거 잘 리턴되는지 봐야함
    }

    @Transactional
    public PostsResponseDto detail(String mynickname, Long pid) {
        List<PostsListResponseDto> postlist = new ArrayList<PostsListResponseDto>();
        List<Comment> comments = commentRepository.findAllByPostsPid(pid);
        Optional<User> user = userRepository.findByNickname(mynickname);
        Long userid = user.get().getUid();
        Likes likes = likesRepository.findByPostsPidAndUserUid(pid, userid);
        boolean islike = false;
        if(likes != null ) islike = true;
        PostsResponseDto postsResponseDto = new PostsResponseDto(comments, islike);
        return postsResponseDto;
    }

    @Transactional
    public PostsResponseDto2 detail2(Long pid) {
        Posts post = postsRepository.findByPid(pid);
        String nickname = post.getUser().getNickname();
        String img = post.getImg();
        String contents = post.getContent();
        List<String> hashtags = new ArrayList<>();
        List<Tags> tags = tagsRepository.findAllByPostsPid(pid);
        Long likecount = likesRepository.countLikesByPostsPid(pid);
        String date = post.getModifiedDate().toString();
        if(tags != null) {
            for(int j=0; j<tags.size(); j++){
                hashtags.add(hashtagsRepository.findByHid(tags.get(j).getHashtags().getHid()).getContent());
            }
        }
        return new PostsResponseDto2(nickname, img, contents, hashtags, date, likecount);
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) { // 게시글 게시 요청
        User user = userRepository.findByNickname(requestDto.getNickname()).orElse(null);
        Long pid = postsRepository.save(requestDto.toEntity(user)).getPid();
        String taglist = requestDto.getHashtags();
        Posts posts = postsRepository.findByPid(pid);
        if(taglist != null) {
            StringTokenizer st = new StringTokenizer(taglist, "#");
            int a = st.countTokens();
            for(int i=0; i<a; i++) {
                String tag = st.nextToken().trim();
                if(tag.contains(" ")){
                    StringTokenizer st1 = new StringTokenizer(tag, " ");
                    String tag1 = st1.nextToken().trim();
                    Hashtags checkhashtags = hashtagsRepository.findByContent(tag1);
                    if(checkhashtags == null) {
                        Long hid = hashtagsRepository.save(Hashtags.builder().content(tag1).build()).getHid();
                        Hashtags hashtags = hashtagsRepository.findByHid(hid);
                        tagsRepository.save(Tags.builder().hashtags(hashtags).posts(posts).build());
                    }
                    else {
                        tagsRepository.save(Tags.builder().hashtags(checkhashtags).posts(posts).build());
                    }
                }else {
                    Hashtags checkhashtags = hashtagsRepository.findByContent(tag);
                    if(checkhashtags == null) {
                        Long hid = hashtagsRepository.save(Hashtags.builder().content(tag).build()).getHid();
                        Hashtags hashtags = hashtagsRepository.findByHid(hid);
                        tagsRepository.save(Tags.builder().hashtags(hashtags).posts(posts).build());
                    }
                    else {
                        tagsRepository.save(Tags.builder().hashtags(checkhashtags).posts(posts).build());
                    }
                }

            }
        }
        return user.getUid();
    }

    @Transactional
    public void update(Long pid, PostsUpdateRequestDto requestDto) { //게시글 수정 요청
        Posts posts = postsRepository.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + pid));
        posts.update(requestDto.getContent());
    }

    @Transactional
    public void delete(Long pid) {
        Posts posts = postsRepository.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + pid));
        postsRepository.delete(posts);
    }

    @Transactional
    public String likescount() {
        //String postsLikesResponseDtos = likesRepository.likescount();
        //return postsLikesResponseDtos;
        return "";
    }

    @Transactional
    public boolean likesup(PostsLikesupRequestDto likesupRequestDto) {

        User user = userRepository.findByNickname(likesupRequestDto.getNickname()).orElse(null);
        Posts posts = postsRepository.findById(likesupRequestDto.getPid()).orElse(null);
        Likes likes = likesRepository.findByPostsPidAndUserUid(posts.getPid(), user.getUid());
        if(likes != null) {
            likesRepository.delete(likes);
            return false;
        }else {
            likesRepository.save(Likes.builder().user(user).posts(posts).build());
            return true;
        }
    }

    @Transactional
    public List<String> likeslist(Long pid) {
        List<Likes> likeslist = likesRepository.findAllByPostsPid(pid);
        List<String> list = new ArrayList<>();
        if(likeslist != null) {
            for(int i=0; i<likeslist.size(); i++){
                list.add(likeslist.get(i).getUser().getNickname());
            }
        }
        return list;
    }
}