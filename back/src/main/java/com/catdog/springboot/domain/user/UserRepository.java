package com.catdog.springboot.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long >  {

    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    //    @Query("SELECT u FROM User u ORDER BY u.id DESC")
//    List<User> findAllDesc();
    @Query(value = "select * from user where user.nickname like %?1%" , nativeQuery = true)
    List<Optional<User>> searchuser(String nickname);

}