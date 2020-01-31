package com.catdog.springboot.domain.test;

import com.catdog.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository extends JpaRepository<Test,Long> {

    @Query("SELECT p FROM Test p ORDER BY p.id DESC")
    List<Test> findAllDesc();
}


