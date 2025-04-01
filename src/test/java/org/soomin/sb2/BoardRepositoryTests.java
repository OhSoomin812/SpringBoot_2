package org.soomin.sb2;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.entities.BoardEntity;
import org.soomin.sb2.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired(required = false)
    BoardRepository repository;

    @Test
    public void testInsert() {
        for(int i = 0; i < 123; i++) {
            BoardEntity boardEntity = BoardEntity.builder()
                    .title("Test" + i)
                    .content("Test Content" + i)
                    .writer("user" + (i % 10))
                    .build();

            repository.save(boardEntity);
        }
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        pageRequestDTO.setType("TCW");
        pageRequestDTO.setKeyword("11");

        log.info(repository.list(pageRequestDTO));

    }
}
