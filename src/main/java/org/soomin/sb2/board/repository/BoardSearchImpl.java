package org.soomin.sb2.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.board.dto.BoardListDTO;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.dto.PageResponseDTO;
import org.soomin.sb2.board.entities.BoardEntity;
import org.soomin.sb2.board.entities.QBoardEntity;
import org.soomin.sb2.reply.entities.QReplyEntity;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class BoardSearchImpl implements BoardSearch{

    private final JPQLQueryFactory queryFactory;

    @Override
    public PageResponseDTO<BoardListDTO> list(PageRequestDTO pageRequestDTO) {
        QBoardEntity board = QBoardEntity.boardEntity;
        QReplyEntity reply = QReplyEntity.replyEntity;

        JPQLQuery<BoardEntity> query = queryFactory.selectFrom(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        query.where(board.bno.gt(0L));
        query.where(board.delFlag.eq(false));

        // 검색 조건
        BooleanBuilder builder = new BooleanBuilder();
        String[] types = pageRequestDTO.getArr();   // ['T', 'C', 'W']

        if(types != null  && types.length > 0) {

            String keyword = pageRequestDTO.getKeyword();

            for(String type : types) {
                if(type.equals("T")) {
                    builder.or(board.title.contains((keyword)));
                }else if(type.equals("C")) {
                    builder.or(board.content.contains((keyword)));
                }else if(type.equals("W")) {
                    builder.or(board.writer.contains((keyword)));
                }
            }// end for

            query.where(builder);
        }// end if

        query.groupBy(board);

        query.limit(pageRequestDTO.getLimit());
        query.offset(pageRequestDTO.getOffset());

        query.orderBy(new OrderSpecifier<>(Order.DESC, board.bno));

        JPQLQuery<BoardListDTO> dtoQuery = query.select(Projections.bean(BoardListDTO.class,
                board.bno, board.title, board.writer, board.viewCnt, board.regDate, reply.rno.count().as("replyCnt")));

        long count = dtoQuery.fetchCount();

        List<BoardListDTO> dtoList = dtoQuery.fetch();

        log.info(dtoList);
        log.info(count);

        return PageResponseDTO.<BoardListDTO>withAll()
                .dtoList(dtoList)
                .total((int)count)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
