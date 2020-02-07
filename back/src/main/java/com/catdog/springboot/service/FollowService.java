package com.catdog.springboot.service;


import com.catdog.springboot.domain.follow.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;

//    @Transactional
//    public Long FollowCount_Cal(Long uid) {
//        return followRepository.count(uid);
//    }
}
