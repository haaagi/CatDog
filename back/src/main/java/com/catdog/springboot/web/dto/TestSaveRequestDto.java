package com.catdog.springboot.web.dto;

import com.catdog.springboot.domain.posts.Posts;
import com.catdog.springboot.domain.test.Test;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestSaveRequestDto {

    private String title;

    @Builder
    public TestSaveRequestDto(String title) {
        this.title = title;
    }

    public Test toEntity() {
        return Test.builder()
                .title(title)
                .build();
    }
}
