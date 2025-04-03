package org.soomin.sb2.board.service;

import org.soomin.sb2.board.dto.*;

public interface BoardService {

    PageResponseDTO<BoardListDTO> list(PageRequestDTO requestDTO);

    BoardReadDTO get(Long bno);

    void register(BoardRegisterDTO registerDTO);
}
