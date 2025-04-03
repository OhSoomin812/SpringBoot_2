package org.soomin.sb2.reply;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.board.entities.BoardEntity;
import org.soomin.sb2.reply.dto.ReplyListDTO;
import org.soomin.sb2.reply.dto.ReplyReadDTO;
import org.soomin.sb2.reply.entities.ReplyEntity;
import org.soomin.sb2.reply.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired(required = false)
    private ReplyRepository repository;

    @Test
    public void testInsert() {

        for(int i = 0; i < 25; i++) {
            // 가짜 BoardEntity 필요 (제일 중요 !!!)
            BoardEntity board = BoardEntity.builder().bno(123L).build();

            ReplyEntity reply = ReplyEntity.builder()
                    .replyText("댓글입니다.")
                    .replyer("user00")
                    .board(board)
                    .build();

            repository.save(reply);
        }
    }

    @Test
    public void testRead() {
        Long rno = 1L;

        Optional<ReplyEntity> result = repository.findById(rno);
        ReplyEntity reply = result.orElseThrow();

        log.info("Reply Read: " + reply);
    }

    @Test
    public void testList() {
        Long rno = 123L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());

        repository.listOfBoard(rno, pageable);
    }

    @Test
    public void testList2() {
        Long rno = 123L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());

        Page<Object[]> result = repository.listOfBoard2(rno, pageable);
        result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testList3() {
        Long rno = 123L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());

        Page<ReplyListDTO> result = repository.listOfBoard3(rno, pageable);
        result.getContent().forEach(log::info);
    }

    @Test
    public void testSelectOne() {
        Long rno = 26L;

        ReplyReadDTO dto = repository.selectOne(rno);

        log.info(dto);
    }

    @Test
    @Transactional
    @Commit
    public void testUpdate() {
        Long rno = 1L;
        String text = "Reply 1 Updated...";

        repository.updateOne(text, rno);
    }

    @Test
    public void testListOfBoardQuerydsl() {

        Long bno = 123L;

        PageRequestDTO requestDTO = new PageRequestDTO(); //1, 10

        PageResponseDTO<ReplyListDTO> res = repository.listQuerydsl(bno, requestDTO);

        log.info(res);
    }
}