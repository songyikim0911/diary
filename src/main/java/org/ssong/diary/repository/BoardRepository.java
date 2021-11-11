package org.ssong.diary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssong.diary.entity.Board;
import org.ssong.diary.repository.search.BoardSearch;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {


    @Query("select b.bno, b.title, b.writer, b.regDate, count(r) from Board b left join Reply r on r.board = b group by b")//left outerjoin, 댓글갯수를 가져와야하기떄문!
    Page<Object[]> ex1(Pageable pageable);//Page<Object[]>는, 배열안에 배열이 있기때문, b와 r이 배열로 들어가있어야한다.
    //inner join을 쓸 수 없고, 없는쪽을 채워서 써야하는 outer join을 써야함,
    //board를 기준으로 모든것을 압축시켜야함. 그래서 groupby가 필요함.
}
