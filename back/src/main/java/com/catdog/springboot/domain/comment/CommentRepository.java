package com.catdog.springboot.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long > {
    //    @Query("SELECT Comment.* FROM Comment.pid  Posts.pid where p.pid = ?1")
    List<Comment> findAllByPostsPid(Long pid);
}