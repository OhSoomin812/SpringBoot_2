package org.soomin.sb2.reply.service;

import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.board.entities.BoardEntity;
import org.soomin.sb2.reply.dto.ReplyAddDTO;
import org.soomin.sb2.reply.dto.ReplyListDTO;
import org.soomin.sb2.reply.dto.ReplyReadDTO;
import org.soomin.sb2.reply.entities.ReplyEntity;
import org.soomin.sb2.reply.exception.ReplyException;
import org.springframework.data.repository.query.Param;

public interface ReplyService {

    Long add(ReplyAddDTO addDTO)throws ReplyException;

    default ReplyEntity dtoToEntity(ReplyAddDTO addDTO) throws ReplyException{

        return ReplyEntity.builder()
                .replyText(addDTO.getReplyText())
                .replyer(addDTO.getReplyer())
                .board(BoardEntity.builder().bno(addDTO.getBno()).build())
                .build();
    }

    ReplyReadDTO get(Long rno) throws ReplyException;

    PageResponseDTO<ReplyListDTO> getListOfBoard(Long bno, PageRequestDTO requestDTO) throws ReplyException;
}
