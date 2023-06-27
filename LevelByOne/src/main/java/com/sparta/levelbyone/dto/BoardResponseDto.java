package com.sparta.levelbyone.dto;

import com.sparta.levelbyone.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BoardResponseDto {
    Long id;
    String title;
    String username;
    String contents;
    LocalDateTime createdAt;

    public BoardResponseDto(Board board) {

        this.id = board.getId();
        this.username = board.getUsername();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();
    }
}