package com.catdog.springboot.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    List<Likes>  findAllByPostsPid (Long pid);
    Long countLikesByPostsPid(Long pid);
    Likes findByPostsPidAndUserUid(Long pid, Long uid);
    Long deleteLikesByLid(Long lid);
}