package com.catdog.springboot.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TagsRepository extends JpaRepository<Tags,Long> {
    List<Tags> findAllByPostsPid(Long pid);
    Tags findByPostsPidAndHashtagsHid(Long pid, Long hid);
    @Query(value = "select pid from tags where hid = ?1", nativeQuery = true)
    List<Long> findpids(Long pid);

    @Query(value = "select count(*) from tags where hid = ?1" , nativeQuery = true)
    Long content_cnt(Long hid);
}