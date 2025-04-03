package org.soomin.sb2.reply.entities;

import jakarta.persistence.*;
import lombok.*;
import org.soomin.sb2.board.entities.BoardEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = AuditingEntityListener.class)
@Entity
@Table(name="tbl_reply", indexes = {
        @Index(name = "idx_board", columnList = " board_bno")
})
@Getter
@ToString(exclude = {"board"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardEntity board;

    @CreatedDate
    @Column(name="regDate", nullable = false)
    protected LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="modDate")
    protected LocalDateTime modDate;

    public void changeReplyText(String text){
        this.replyText = text;
    }
}
