package org.soomin.sb2.todo.service;

import org.soomin.sb2.todo.dto.TodoDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TodoService {

    TodoDTO getOne(Long tno);
}
