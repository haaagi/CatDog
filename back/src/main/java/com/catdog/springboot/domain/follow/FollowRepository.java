package com.catdog.springboot.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository <Follow , Long > {

//    Long countByFollow_request
}
