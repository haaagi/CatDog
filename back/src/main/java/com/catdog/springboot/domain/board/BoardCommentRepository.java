package com.catdog.springboot.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long > {
    List<BoardComment> findAllByBoardBid(Long bid);
}
