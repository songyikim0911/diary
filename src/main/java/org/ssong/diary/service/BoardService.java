package org.ssong.diary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.ssong.diary.dto.BoardDTO;
import org.ssong.diary.dto.BoardListDTO;
import org.ssong.diary.dto.PageRequestDTO;
import org.ssong.diary.dto.PageResponseDTO;
import org.ssong.diary.entity.Board;

@Transactional
public interface BoardService {

    Long register(BoardDTO boardDTO);

    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListDTO> getListWithReplyCount(PageRequestDTO pageRequestDTO);

    BoardDTO read(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

}
