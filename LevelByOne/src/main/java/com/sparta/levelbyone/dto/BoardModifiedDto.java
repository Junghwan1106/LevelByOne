package com.sparta.levelbyone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardModifiedDto {
    String title;
    String username;
    String password;
    String contents;

    @Override
    public String toString() {
        return "BoardModifiedDto{" +
                "title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
