package com.sparta.levelbyone.service;

import com.sparta.levelbyone.dto.BoardModifiedDto;
import com.sparta.levelbyone.dto.BoardRequestDeleteDto;
import com.sparta.levelbyone.dto.BoardResponseDeleteDto;
import com.sparta.levelbyone.dto.BoardResponseDto;
import com.sparta.levelbyone.entity.Board;
import com.sparta.levelbyone.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j(topic = "Controller Log")
@Service
@AllArgsConstructor
public class BoardService {

    //BoardRepositoryList boardRepositoryList = new BoardRepositoryList();

    BoardRepository boardRepository;

//    Long BoardID = 1L;

    public List<BoardResponseDto> GetListBoard(){
        /* 내부용
        return boardRepositoryList.GetListBoard()
                .stream().map(BoardResponseDto::new).toList();
                */
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto CreateBoard(BoardModifiedDto requestDto){
        log.error("Controller CreateBoard join " + requestDto.toString());

        Board newBoard = new Board(requestDto);
        log.error("Create Board " + newBoard.toString());

        log.error("Save Board");
        newBoard = boardRepository.save(newBoard);

        /* 내부용
        newBoard = boardRepositoryList.save(newBoard);
        */

        // 게시글 반환
        log.error("Return Board");
        return new BoardResponseDto(newBoard);
    }

    public BoardResponseDto SelectBoard(long id){
        //선택한 게시글 조회
        log.error("Controller SelectBoard join");

        log.error("Repository Search Method");

        Board returnBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Find!")
        );
        /* 내부용
        Board returnBoard = boardRepositoryList.findById(id);
        */
        log.error("result Data =" + returnBoard.toString());

        // 게시글 반환
        log.error("Return Board");
        return new BoardResponseDto(returnBoard);
    }

    @Transactional
    public BoardResponseDto SelectModifiedBoard(long id, BoardModifiedDto requestDto){
        //선택한 게시글 수정
        log.error("Controller SelectModifiedBoard join");

        //DB에서 검색
        log.error("Search DB for id");
        Board returnBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Find!")
        );
        /*
        Board returnBoard = boardRepositoryList.findById(id);
         */
        log.error("result Data =" + returnBoard.toString());

        if(returnBoard.getPassword().equals(requestDto.getPassword())){

            // 수정하는 내용
            if(!requestDto.getTitle().isEmpty())
                returnBoard.updateTitle(requestDto.getTitle());
            if(!requestDto.getContents().isEmpty())
                returnBoard.updateContents(requestDto.getContents());

            //returnBoard = boardRepositoryList.update(returnBoard);
        } else
            return null;
        // 게시글 반환
        log.error("Return Board");
        return new BoardResponseDto(returnBoard);
    }


    public BoardResponseDeleteDto SelectDeleteBoard(long id, BoardRequestDeleteDto requestDto){
        log.error("Controller SelectDeleteBoard join");

        Board returnBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Find!")
        );
        /* 내부용
        Board returnBoard = boardRepositoryList.findById(id);
        log.error("result Data =" + returnBoard.toString());
        */

        BoardResponseDeleteDto response;
        if(returnBoard.getPassword().equals(requestDto.getPassword())){
            boardRepository.delete(returnBoard);
            //메세지 출력
            response = new BoardResponseDeleteDto("삭제 됐졍!!");
        } else
            response = new BoardResponseDeleteDto("삭제 안 됐졍");

        // 게시글 반환
        log.error("Return Result");
        return response;
    }
}
