package com.catdog.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    //인터페이스 생성 후 JpaRepository<Entity 클래스, PK 타입> 상속시
    //기본적인 CRUD 메소드가 자동으로 생성됩니다.

    // 주의점
    // Entity 클래스와 기본 Entity Repository는 함께 위치 해야 합니다.
    Posts findByPid(Long id);
    List<Posts> findAllByOrderByCreatedDateDesc();

    @Query(value ="select * from posts where uid in (select followresponse_uid from follow where followrequest_uid = ?1)", nativeQuery = true)
    List<Posts> followingpostList(Long uid);

    @Query(value = "select * from posts where posts.uid = ?1" , nativeQuery = true)
    List<Posts> postList(Long uid);

    @Query(value = "select count(*) from posts where uid = ?1" , nativeQuery = true)
    Long cnt_post(Long uid);

    @Query(value = "select * from posts where pid = ?1", nativeQuery = true)
    Posts findAllByPid(Long pid);

}
