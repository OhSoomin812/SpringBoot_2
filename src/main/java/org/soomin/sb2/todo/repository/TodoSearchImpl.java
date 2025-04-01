package org.soomin.sb2.todo.repository;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.todo.entities.QTodo;
import org.soomin.sb2.todo.entities.Todo;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class TodoSearchImpl implements TodoSearch{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Todo> list1(Pageable pageable) {

        log.info("list................................");
        log.info(queryFactory);

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = queryFactory.selectFrom(todo);

        int size = pageable.getPageSize();
        int offset = pageable.getPageNumber() * size;

        query.limit(size);
        query.offset(offset);

        List<Todo> list = query.fetch();

        return list;
    }
}
