package org.soomin.sb2.reply.repository;

import org.soomin.sb2.reply.dto.ReplyListDTO;
import org.soomin.sb2.reply.dto.ReplyReadDTO;
import org.soomin.sb2.reply.entities.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long>, ReplySearch {

    @Query("SELECT r FROM ReplyEntity r WHERE r.board.bno = :bno")
    Page<ReplyEntity> listOfBoard(@Param("bno") Long bno, Pageable pageable);

    @Query("SELECT r.rno, r.replyText, r.replyer, r.board.bno, r.regDate, r.modDate " +
            "FROM ReplyEntity r WHERE r.board.bno = :bno")
    Page<Object[]> listOfBoard2(@Param("bno") Long bno, Pageable pageable);

    @Query("SELECT new org.soomin.sb2.reply.dto.ReplyListDTO (r.rno, r.replyText, r.replyer, r.board.bno, r.regDate, " +
            "r.modDate) FROM ReplyEntity r WHERE r.board.bno = :bno")
    Page<ReplyListDTO> listOfBoard3(@Param("bno") Long bno, Pageable pageable);

    @Query("SELECT new org.soomin.sb2.reply.dto.ReplyReadDTO(r) FROM ReplyEntity r WHERE r.rno = :rno")
    ReplyReadDTO selectOne(@Param("rno") Long rno);

    @Modifying
    @Query("UPDATE ReplyEntity r SET r.replyText = :text, r.modDate = CURRENT_TIMESTAMP WHERE r.rno = :rno")
    int updateOne(@Param("text") String text, @Param("rno") Long rno);


    @Modifying
    @Query("DELETE FROM ReplyEntity r WHERE r.rno = :rno")
    int deleteOne(@Param("rno") Long rno);
}
