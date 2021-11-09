package org.ssong.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssong.diary.entity.Board;
import org.ssong.diary.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

}
