package com.sparta.levelbyone.entity;

import com.sparta.levelbyone.dto.BoardModifiedDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {
// 데이터 베이스의 컬럼값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String title;

    private String contents;

    //작성 날짜
    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    //수정 날짜
    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime ModifiedAt;


    public Board(BoardModifiedDto requestDto) {
//        this.id = id;
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    //바뀌어야하는 생성자를 만들기
    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }

//    @Override
//    public String toString() {
//        return "Board{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", title='" + title + '\'' +
//                ", contents='" + contents + '\'' +
//                ", CreateAt='" + CreateAt + '\'' +
//                ", ModifiedAt='" + ModifiedAt + '\'' +
//                '}';
//    }
}
