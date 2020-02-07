package com.catdog.springboot.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {

    Long countLikesByPostsPid(Long pid);

}