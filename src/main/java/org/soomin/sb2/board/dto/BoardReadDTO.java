package org.soomin.sb2.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardReadDTO {

    private Long bno;

    private String title, content, writer;

    private boolean delFlag;

    private int viewCnt;

    private LocalDateTime regDate, modDate;

    // bno, title, content, writer, delFlag, viewCnt, regDate, modDate
    public BoardReadDTO(Long bno, String title, String content, String writer, boolean delFlag, int viewCnt,
                        LocalDateTime regDate, LocalDateTime modDate) {
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.delFlag = delFlag;
        this.viewCnt = viewCnt;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
