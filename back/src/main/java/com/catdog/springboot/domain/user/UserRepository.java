package com.catdog.springboot.domain.user;


import com.catdog.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long >  {

    Optional<User> findByEmail(String email);
//    @Query("SELECT u FROM User u ORDER BY u.id DESC")
//    List<User> findAllDesc();
}

