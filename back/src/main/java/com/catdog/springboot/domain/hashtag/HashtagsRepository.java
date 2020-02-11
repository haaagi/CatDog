package com.catdog.springboot.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface HashtagsRepository extends JpaRepository<Hashtags,Long> {
    Hashtags findByContent(String tags);
    Hashtags findByHid(Long hid);

    @Query(value =  "select * from hashtags where hashtags.content like %?1%", nativeQuery = true)
    List<String> searchtag(String keyword);
}