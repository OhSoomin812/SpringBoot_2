package org.soomin.sb2.reply.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.reply.dto.ReplyListDTO;
import org.soomin.sb2.reply.entities.QReplyEntity;
import org.soomin.sb2.reply.entities.ReplyEntity;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class ReplySearchImpl implements ReplySearch{

    private final JPAQueryFactory queryFactory;

    @Override
    public PageResponseDTO<ReplyListDTO> listQuerydsl(Long bno, PageRequestDTO requestDTO) {

        QReplyEntity replyEntity = QReplyEntity.replyEntity;

        JPQLQuery<ReplyEntity> query = queryFactory.selectFrom(replyEntity);

        query.where(replyEntity.board.bno.eq(bno));

        query.orderBy(new OrderSpecifier<>(Order.ASC, replyEntity.rno));
        query.limit(requestDTO.getLimit());
        query.offset(requestDTO.getOffset());

        JPQLQuery<ReplyListDTO> dtoQuery = query.select(Projections.bean(ReplyListDTO.class
                , replyEntity.rno, replyEntity.replyText, replyEntity.replyer, replyEntity.board.bno.as("bno")
                , replyEntity.regDate, replyEntity.modDate));

        List<ReplyListDTO> dtoList = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();

        return PageResponseDTO.<ReplyListDTO>withAll()
                .dtoList(dtoList)
                .total((int)total)
                .pageRequestDTO(requestDTO)
                .build();
    }
}
