package org.soomin.sb2.board.repository;

import org.soomin.sb2.board.entities.BoardEntity;
import org.soomin.sb2.todo.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardSearch {
}
