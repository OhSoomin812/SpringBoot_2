package org.soomin.sb2.todo.repository;

import org.soomin.sb2.todo.entities.Todo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoSearch {

    List<Todo> list1(Pageable pageable);
}
