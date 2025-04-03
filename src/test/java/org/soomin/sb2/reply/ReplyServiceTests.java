package org.soomin.sb2.reply;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.reply.dto.ReplyAddDTO;
import org.soomin.sb2.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired(required = false)
    private ReplyService service;

    @Test
    public void testAdd() {
        Long bno = 122L;

        for(int i = 0; i < 3; i++) {
            ReplyAddDTO addDTO = ReplyAddDTO.builder()
                    .replyText("테스트 댓글")
                    .replyer("user00")
                    .bno(bno)
                    .build();

            log.info(service.add(addDTO));
        }
    }

    @Test
    public void testRead() {
        Long rno = 29L;

        log.info(service.get(rno));
    }

    @Test
    public void testListOfBoard() {
        PageRequestDTO requestDTO = new PageRequestDTO();
        Long bno = 123L;

        log.info(service.getListOfBoard(bno, requestDTO));
    }
}
