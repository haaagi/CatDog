package com.catdog.springboot.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HashtagsRepository extends JpaRepository<Hashtags,Long> {
    Optional<Hashtags> findByContent(String tags);
}