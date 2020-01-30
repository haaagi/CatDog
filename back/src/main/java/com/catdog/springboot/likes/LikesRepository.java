package com.catdog.springboot.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long> {
/*    @Query("select new PostsLikesResponseDto(pid , count(pid)) from Likes l group by pid")
    List<PostsLikesResponseDto> likescount();*/

    //
    @Query(value="SELECT pid , count(pid) count FROM likes GROUP BY pid", nativeQuery=true)
    List<Object[]> likescount();
}