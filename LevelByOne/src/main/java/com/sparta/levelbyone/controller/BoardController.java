package com.sparta.levelbyone.controller;


import com.sparta.levelbyone.dto.BoardModifiedDto;
import com.sparta.levelbyone.dto.BoardRequestDeleteDto;
import com.sparta.levelbyone.dto.BoardResponseDeleteDto;
import com.sparta.levelbyone.dto.BoardResponseDto;
import com.sparta.levelbyone.repository.BoardRepository;
import com.sparta.levelbyone.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j(topic = "Controller Log")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BoardController {

    BoardService boardService;

    @GetMapping("/boards")
    public List<BoardResponseDto> GetListBoard(){
        return boardService.GetListBoard();
    }

    @PostMapping("/board")
    public BoardResponseDto CreateBoard(@RequestBody BoardModifiedDto requestDto){

        return boardService.CreateBoard(requestDto);
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto SelectBoard(@PathVariable long id){
        return boardService.SelectBoard(id);
    }

    @PutMapping("/board/{id}")
    public BoardResponseDto SelectModifiedBoard(@PathVariable long id,
                                                @RequestBody BoardModifiedDto requestDto){

        return boardService.SelectModifiedBoard(id, requestDto);
    }

    @DeleteMapping("/board/{id}")
    public BoardResponseDeleteDto SelectDeleteBoard(@PathVariable long id,
                                    @RequestBody BoardRequestDeleteDto requestDto){

        return boardService.SelectDeleteBoard(id, requestDto);
    }
}
