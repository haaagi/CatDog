package com.catdog.springboot.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository <Follow , Long > {


    @Query(value = "select count(*) from follow where follow_request = ?1" , nativeQuery = true)
    Long follow_F_Count(Long uid);

    @Query(value = "select count(*) from follow where follow_response = ?1" , nativeQuery = true)
    Long follow_T_Count(Long uid);
}
