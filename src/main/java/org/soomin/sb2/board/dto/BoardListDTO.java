package org.soomin.sb2.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class BoardListDTO {

    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private int viewCnt;

    private long replyCnt;

}
