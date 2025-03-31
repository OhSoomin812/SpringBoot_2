package org.soomin.sb2.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.todo.dto.TodoDTO;
import org.soomin.sb2.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService{

    private TodoRepository repository;

    @Override
    public TodoDTO getOne(Long tno) {
        // 조회하면 Optional<Todo>
        
        // Todo를 꺼내서 TodoDTO로 변환
        
        return null;
    }
}
