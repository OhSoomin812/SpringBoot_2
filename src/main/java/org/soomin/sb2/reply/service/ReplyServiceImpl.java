package org.soomin.sb2.reply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.reply.dto.ReplyAddDTO;
import org.soomin.sb2.reply.dto.ReplyListDTO;
import org.soomin.sb2.reply.dto.ReplyReadDTO;
import org.soomin.sb2.reply.entities.ReplyEntity;
import org.soomin.sb2.reply.exception.ReplyException;
import org.soomin.sb2.reply.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository repository;
    private final ReplyRepository replyRepository;

    @Override
    public Long add(ReplyAddDTO addDTO) throws ReplyException {
        try {
            ReplyEntity entity = dtoToEntity(addDTO);
            replyRepository.save(entity);
            return entity.getRno();

        }catch (Exception e) {
            throw new ReplyException(400);
        }
    }

    @Override
    public ReplyReadDTO get(Long rno) throws ReplyException {
        ReplyReadDTO dto = replyRepository.selectOne(rno);

        if(dto == null){
            throw new ReplyException(404);
        }

        return dto;
    }

    @Override
    public PageResponseDTO<ReplyListDTO> getListOfBoard(Long bno, PageRequestDTO requestDTO) throws ReplyException {
        PageResponseDTO<ReplyListDTO> responseDTO = replyRepository.listQuerydsl(bno, requestDTO);


        // 이런식으로도 처리할 수는 있다.
//        if(responseDTO.getDtoList() == null){
//            throw new ReplyException(404);
//        }

        return responseDTO;
    }
}
