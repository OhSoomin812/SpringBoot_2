package org.soomin.sb2.board.repository;

import org.soomin.sb2.board.dto.BoardReadDTO;
import org.soomin.sb2.board.entities.BoardEntity;
import org.soomin.sb2.todo.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardSearch {

    @Query("SELECT new org.soomin.sb2.board.dto.BoardReadDTO(b.bno, b.title, b.content, b.writer, " +
            "b.delFlag, b.viewCnt, b.regDate, b.modDate) FROM BoardEntity b WHERE b.bno = :bno")
    BoardReadDTO selectOne(@Param("bno") Long bno);
}
