package org.ssong.diary.service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.ssong.diary.dto.PageRequestDTO;
import org.ssong.diary.dto.PageResponseDTO;
import org.ssong.diary.dto.ReplyDTO;

@Transactional
public interface ReplyService {

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}
