package org.soomin.sb2.todo.repository;

import org.soomin.sb2.todo.dto.TodoDTO;
import org.soomin.sb2.todo.entities.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TodoRepository extends JpaRepository<Todo, Long>{

    @Query("SELECT t FROM Todo t WHERE t.title LIKE %:title% ") //SQL 아님
    Page<Todo> getTitle(@Param("title") String title, Pageable pageable);

    @Query("SELECT new org.soomin.sb2.todo.dto.TodoDTO(t.tno, t.title, t.writer, t.regDate, t.modDate) from Todo t WHERE t.tno = :tno")
    TodoDTO selectDTO(@Param("tno") Long tno);


}