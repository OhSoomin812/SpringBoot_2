package org.soomin.sb2.board.repository;

import org.soomin.sb2.board.dto.BoardListDTO;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;

public interface BoardSearch {

    PageResponseDTO<BoardListDTO> list(PageRequestDTO pageRequestDTO);
}
