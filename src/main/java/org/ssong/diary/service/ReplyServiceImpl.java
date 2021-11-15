package org.ssong.diary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ssong.diary.dto.PageRequestDTO;
import org.ssong.diary.dto.PageResponseDTO;
import org.ssong.diary.dto.ReplyDTO;
import org.ssong.diary.entity.Reply;
import org.ssong.diary.repository.ReplyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ModelMapper modelMapper;

    private final ReplyRepository replyRepository;


    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = null;

        //pageRequestDTO안의 page값이 -1이라면, 우리가 test에서 만든 로직을 사용하여 값을 변경해줘야함.
        if(pageRequestDTO.getPage() == -1){
            int lastPage = calcLastPage(bno, pageRequestDTO.getSize()); // -1 : 댓글이 없는경우, 숫자 마지막 댓글 페이지
            if(lastPage <= 0){
                lastPage = 1;
            }
            pageRequestDTO.setPage(lastPage);
        }

        pageable =  PageRequest.of(pageRequestDTO.getPage() -1, pageRequestDTO.getSize());


        Page<Reply> result = replyRepository.getListByBno(bno,pageable);

        List<ReplyDTO> dtoList = result.get().map(reply->
                        modelMapper.map(reply,ReplyDTO.class))
                .collect(Collectors.toList());

        //  dtoList.forEach(replyDTO->log.info(replyDTO));

        return new PageResponseDTO<>( pageRequestDTO, (int)result.getTotalElements(),dtoList);
    }

    private int calcLastPage(Long bno, double size) {//마지막 페이지 계산 메서드

        int count = replyRepository.getReplyCountOfBoard(bno);
        int lastPage = (int)(Math.ceil(count/size));

        return lastPage;
    }
}
