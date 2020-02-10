package com.catdog.springboot.service;

import com.catdog.springboot.domain.comment.Comment;
import com.catdog.springboot.domain.comment.CommentRepository;
import com.catdog.springboot.domain.hashtag.Hashtags;
import com.catdog.springboot.domain.hashtag.HashtagsRepository;
import com.catdog.springboot.domain.hashtag.Tags;
import com.catdog.springboot.domain.hashtag.TagsRepository;
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
    @Transactional
    public List<PostsListResponseDto> findAll() {
        List<PostsListResponseDto> postlist = new ArrayList<PostsListResponseDto>();
        List<Posts> posts = postsRepository.findAllByOrderByCreatedDateDesc();
        if(posts != null) {
            for(int i=0; i<posts.size(); i++){
                Long pid = posts.get(i).getPid();
                String nickname = posts.get(i).getUser().getNickname();
                String img = posts.get(i).getImg();
                String contents = posts.get(i).getContent();
                List<String> hashtags = new ArrayList<>();
                List<Tags> tags = tagsRepository.findAllByPostsPid(pid);
                List<Comment> comments = commentRepository.findAllByPostsPid(pid);
                Long likecount = likesRepository.countLikesByPostsPid(pid);
                String date = posts.get(i).getModifiedDate().toString();
                if(tags != null) {
                    for(int j=0; j<tags.size(); j++){
                        hashtags.add(hashtagsRepository.findByHid(tags.get(j).getHashtags().getHid()).getContent());
                    }
                }
                postlist.add(new PostsListResponseDto(pid, nickname, img, contents, hashtags, comments, date, likecount));
            }
        }


        return postlist; //이거 잘 리턴되는지 봐야함
//
//                postsRepository.findAllByOrderByCreatedDateDesc().stream()
//                .map(PostsListResponseDto::new)
//                .collect(Collectors.toList());
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
    public Long update(Long pid, PostsUpdateRequestDto requestDto) { //게시글 수정 요청
        Posts posts = postsRepository.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + pid));

     //   posts.update(requestDto.getImg(), requestDto.getContent(), requestDto.getHashtags());

        return pid;
    }

    public PostsResponseDto findById(Long pid) {

        Posts entity = postsRepository.findById(pid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + pid));

        return new PostsResponseDto(entity);
    }


    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional
    public String likescount() {
        //String postsLikesResponseDtos = likesRepository.likescount();
        //return postsLikesResponseDtos;
        return "";
    }

    @Transactional
    public Long likesup(PostsLikesupRequestDto likesupRequestDto) {
        User user = userRepository.findByEmail(likesupRequestDto.getEmail()).orElse(null);
        Posts posts = postsRepository.findById(likesupRequestDto.getPid()).orElse(null);
        return likesRepository.save(likesupRequestDto.toEntity(user, posts)).getLid();
    }

//    @Transactional
//    public void savehashtag(String tags) {
//        Hashtags hashtags = hashtagsRepository.findByContent(tags);
//        if(hashtags == null) {
//            hashtagsRepository.save(hashtags.builder().content(tags).build());
//        }else {
//
//        }
//
//    }
}