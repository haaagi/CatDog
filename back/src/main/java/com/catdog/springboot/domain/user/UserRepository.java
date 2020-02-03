package com.catdog.springboot.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long >  {

    Optional<User> findByEmail(String email);
//    @Query("SELECT u FROM User u ORDER BY u.id DESC")
//    List<User> findAllDesc();
}

