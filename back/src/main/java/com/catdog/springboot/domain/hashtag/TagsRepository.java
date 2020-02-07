package com.catdog.springboot.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TagsRepository extends JpaRepository<Tags,Long> {
    List<Tags> findAllByPostsPid(Long pid);
}