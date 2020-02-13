package com.catdog.springboot.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository <Follow , Long > {
    @Query(value = "select count(*) from follow where Followrequest_uid = ?1" , nativeQuery = true)
    Long follow_F_Count(Long uid);

    @Query(value = "select count(*) from follow where Followresponse_uid = ?1" , nativeQuery = true)
    Long follow_T_Count(Long uid);

    @Query(value = "select Followrequest_uid from follow where Followresponse_uid = ?1" , nativeQuery = true)
    List<Long> follow_F_list(Long uid);
    @Query(value = "select Followresponse_uid from follow where Followrequest_uid = ?1" , nativeQuery = true)
    List<Long> follow_T_list(Long uid);


    Follow findByFollower_UidAndFollowing_Uid(Long followeruser_uid, Long followinguser_uid);
    Long deleteFollowByFid(Long fid);
}
