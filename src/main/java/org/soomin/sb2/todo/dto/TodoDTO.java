package org.soomin.sb2.todo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TodoDTO {

    private Long tno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public TodoDTO(Long tno, String title, String writer, LocalDateTime regDate, LocalDateTime modDate) {
        this.tno = tno;
        this.title = title;
        this.writer = writer;
    }

}
