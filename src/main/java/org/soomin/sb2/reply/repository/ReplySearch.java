package org.soomin.sb2.reply.repository;

import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.reply.dto.ReplyListDTO;

public interface ReplySearch {

    PageResponseDTO<ReplyListDTO> listQuerydsl(Long bno, PageRequestDTO requestDTO);
}
