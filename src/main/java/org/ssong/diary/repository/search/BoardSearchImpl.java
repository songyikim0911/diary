package org.ssong.diary.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.ssong.diary.entity.Board;
import org.ssong.diary.entity.QBoard;
import org.ssong.diary.entity.QReply;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{


    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(char[] typeArr, String keyword, Pageable pageable) {
        log.info("----search1");
        QBoard board = QBoard.board;

        JPQLQuery<Board> jpqlQuery = from(board);

//        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
//
//        String keyword="10";
//        char[] typeArr= {'T','C','W'};




        if(typeArr!=null && typeArr.length >0) {

            BooleanBuilder condition = new BooleanBuilder();


            for (char type : typeArr) {
                if (type == 'T') {
                    condition.or(board.title.contains(keyword));
                } else if (type == 'C') {
                    condition.or(board.content.contains(keyword));
                } else if (type == 'W') {
                    condition.or(board.writer.contains(keyword));
                }
            }

            jpqlQuery.where(condition);

        }

        jpqlQuery.where(board.bno.gt(0L));

        JPQLQuery<Board> pagingQuery =
                this.getQuerydsl().applyPagination(pageable,jpqlQuery);


        List<Board> boardList = pagingQuery.fetch();
        long totalCount = pagingQuery.fetchCount();

        return new PageImpl<>(boardList, pageable, totalCount);

    }

    @Override
    public Page<Object[]> searchWithReplyCount() {

        QBoard qBoard = QBoard.board;
        QReply qReply = QReply.reply;

        JPQLQuery<Board> query = from(qBoard);

        query.leftJoin(qReply).on(qReply.board.eq(qBoard));

        query.where(qBoard.bno.eq(200L));

        query.groupBy(qBoard);

        JPQLQuery<Tuple> selectQuery = query.select(qBoard.bno, qBoard.title, qBoard.writer,
                qBoard.regDate, qReply.count());

        log.info(selectQuery);


        return null;
    }
}
