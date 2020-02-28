package com.catdog.springboot.service;


import com.catdog.springboot.domain.board.Board;
import com.catdog.springboot.domain.board.BoardComment;
import com.catdog.springboot.domain.board.BoardCommentRepository;
import com.catdog.springboot.domain.board.BoardRepository;
import com.catdog.springboot.domain.user.User;
import com.catdog.springboot.domain.user.UserRepository;
import com.catdog.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final UserRepository userRepository;
    @Transactional
    public List<BoardListResponseDto> findAll() {
        List<BoardListResponseDto> boardlist = new ArrayList<>();
        List<Board> boards = boardRepository.findAllByOrderByCreatedDateDesc();
        if(boards != null) {
            for(int i=0; i<boards.size(); i++){
                boardlist.add(new BoardListResponseDto(boards.get(i).getBid(), boards.get(i).getType(), boards.get(i).getTitle()
                        , boards.get(i).getContents(),  boards.get(i).getUser().getNickname() ,boards.get(i).getModifiedDate().toString()));
            }
        }
        return boardlist; //이거 잘 리턴되는지 봐야함
    }

    @Transactional
    public List<BoardComment> detail(Long bid) {
        return boardCommentRepository.findAllByBoardBid(bid);
    }

    @Transactional
    public Long save(BoardSaveRequestDto boardSaveRequestDto) {
        User user = userRepository.findAllByNickname(boardSaveRequestDto.getNickname());
        return boardRepository.save(boardSaveRequestDto.toEntity(user)).getBid();
    }

    @Transactional
    public void delete(Long bid) {
        Board board = boardRepository.findAllByBid(bid);
        boardRepository.delete(board);
    }

    @Transactional
    public void update(Long bid, BoardUpdateRequestDto updateDto) {
        Board board = boardRepository.findById(bid)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + bid));
        board.update(updateDto.getContents(), updateDto.getTitle());
    }

    @Transactional
    public List<BoardComment> comment(BoardCommentRequestDto commentRequestDto){
        User user = userRepository.findByNickname(commentRequestDto.getNickname()).orElse(null);
        Board board = boardRepository.findAllByBid(commentRequestDto.getBid());
        boardCommentRepository.save(commentRequestDto.toEntity(user, board, commentRequestDto.getContents()));
        return boardCommentRepository.findAllByBoardBid(board.getBid());
    }

    @Transactional
    public List<BoardComment> commentdelete(Long bid, Long bcid) {
        BoardComment comment = boardCommentRepository.findById(bcid).orElse(null);
        boardCommentRepository.delete(comment);
        Board board = boardRepository.findAllByBid(bid);
        return boardCommentRepository.findAllByBoardBid(board.getBid());
    }


}
