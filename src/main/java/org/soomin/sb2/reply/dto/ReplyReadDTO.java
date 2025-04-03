package org.soomin.sb2.reply.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.soomin.sb2.reply.entities.ReplyEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReplyReadDTO {

    private Long rno;

    private String replyText;

    private String replyer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private Long bno;

    public ReplyReadDTO(ReplyEntity entity){
        this.rno = entity.getRno();
        this.replyText = entity.getReplyText();
        this.replyer = entity.getReplyer();
        this.regDate = entity.getRegDate();
        this.modDate = entity.getModDate();

        this.bno = entity.getBoard().getBno();
    }
}
