package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String email;
    private String img;
    private String content;
    private List<String> hashtags;

    @Builder
    public PostsSaveRequestDto(String email, String img, String content, List hashtags) {
        this.email = email;
        this.img = img;
        this.content = content;
        this.hashtags = hashtags;
    }

    public Posts toEntity(User user) {
        return Posts.builder()
                .img(img)
                .content(content)
                .user(user)
                .build();
    }

    public String ListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<list.size(); i++){
            sb.append(list.get(i));
            if(i != (list.size() - 1)) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }



}
