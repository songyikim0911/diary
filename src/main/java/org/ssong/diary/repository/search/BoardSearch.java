package org.ssong.diary.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.ssong.diary.entity.Board;

public interface BoardSearch {

    Page<Board> search1(char[] typeArr, String keyword, Pageable pageable);

    Page<Object[]> searchWithReplyCount(char[] typeArr, String keyword,Pageable pageable);

}
