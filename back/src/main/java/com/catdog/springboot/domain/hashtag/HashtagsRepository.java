package com.catdog.springboot.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface HashtagsRepository extends JpaRepository<Hashtags,Long> {
    Hashtags findByContent(String tags);
    Hashtags findByHid(Long hid);

    @Query(value =  "select hid from hashtags where hashtags.content like %?1%", nativeQuery = true)
    List<Long> searchtag(String keyword);

    @Query(value = "select hid from hashtags where content = ?1" , nativeQuery = true)
    Long findhid(String content);

    Long countAllByHid(String content);

    @Query(value = "select content from hashtags where hid = ?1" , nativeQuery = true)
    String findContent(Long hid);
}