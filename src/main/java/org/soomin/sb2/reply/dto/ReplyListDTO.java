package org.soomin.sb2.reply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReplyListDTO {

    private Long rno;

    private String replyText;

    private String replyer;

    private Long bno;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-ss")
    private LocalDateTime regDate, modDate;

    public ReplyListDTO(Long rno, String replyText, String replyer, Long bno, LocalDateTime regDate, LocalDateTime modDate) {
        this.rno = rno;
        this.replyText = replyText;
        this.replyer = replyer;
        this.bno = bno;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
