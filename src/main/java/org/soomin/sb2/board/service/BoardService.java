package org.soomin.sb2.board.service;

import org.soomin.sb2.board.dto.BoardListDTO;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;

public interface BoardService {

    PageResponseDTO<BoardListDTO> list(PageRequestDTO requestDTO);
}
