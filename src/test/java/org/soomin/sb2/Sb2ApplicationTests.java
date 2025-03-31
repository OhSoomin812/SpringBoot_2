package org.soomin.sb2;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.soomin.sb2.todo.entities.Todo;
import org.soomin.sb2.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Log4j2
@Transactional
class Sb2ApplicationTests {

    @Autowired(required = false)
    private TodoRepository repository;

    @Test
    @Commit
    public void testInsert(){
        // insert는 save() 하시면 끝

        for(int i = 0; i < 50; i++){
            Todo todo = Todo.builder()
                    .title("Test")
                    .writer("user1")
                    .build();

            repository.save(todo);
        }

    }

    @Disabled
    @Test
    public void testRead() {
        Optional<Todo> result = repository.findById(1L);
        log.info(result.get());
    }

    @Disabled
    @Test
    public void testDelete() {
        Long tno = 1L;

        repository.deleteById(tno);
    }

    @Test
    @Commit
    public void testUpdate() {
        Optional<Todo> result = repository.findById(1L);

        Todo todo = result.get();

        todo.changeTitle("Changed Title");
    }

    @Test
    public void testList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = repository.findAll(pageable);
        result.get().forEach(todo -> log.info(todo));

        log.info("--------------------------------");
        log.info(result.getTotalElements());
        log.info(result.getNumber());
        log.info(result.getSize());
    }

    @Test
    public void testQuery1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        repository.getTitle("AAA", pageable);
    }

    @Test
    public void testSelectDTO() {
        log.info(repository.selectDTO(1L));
    }
}
