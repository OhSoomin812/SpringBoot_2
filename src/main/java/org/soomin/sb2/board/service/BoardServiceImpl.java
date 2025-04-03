package org.soomin.sb2.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.board.dto.*;
import org.soomin.sb2.board.entities.BoardEntity;
import org.soomin.sb2.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;

    @Override
    public PageResponseDTO<BoardListDTO> list(PageRequestDTO requestDTO) {
        return repository.list(requestDTO);
    }

    @Override
    public BoardReadDTO get(Long bno) {
        return repository.selectOne(bno);
    }

    @Override
    public void register(BoardRegisterDTO registerDTO) {
        BoardEntity board = BoardEntity.builder()
                .title(registerDTO.getTitle())
                .content(registerDTO.getContent())
                .writer(registerDTO.getWriter())
                .build();

        repository.save(board);
    }
}
