package com.catdog.springboot.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;


public interface HashtagsRepository extends JpaRepository<Hashtags,Long> {
    Hashtags findByContent(String tags);
    Hashtags findByHid(Long hid);
}