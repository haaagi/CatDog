package com.catdog.springboot.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long >  {

    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    User findAllByNickname(String nickname);

    @Query(value = "select * from user where user.nickname like %?1%" , nativeQuery = true)
    List<Optional<User>> searchuser(String nickname);

}