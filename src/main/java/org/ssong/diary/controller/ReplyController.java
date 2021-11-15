package org.ssong.diary.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssong.diary.dto.PageRequestDTO;
import org.ssong.diary.dto.PageResponseDTO;
import org.ssong.diary.dto.ReplyDTO;
import org.ssong.diary.service.ReplyService;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/replies")
public class ReplyController {

    private ReplyService replyService;

    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getListOfBoard(@PathVariable("bno")Long bno, PageRequestDTO pageRequestDTO){

        return replyService.getListOfBoard(bno, pageRequestDTO);

    }


}
