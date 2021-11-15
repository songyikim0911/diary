package org.ssong.diary.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ssong.diary.dto.PageRequestDTO;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testList(){
        Long bno = 197L;
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().
                page(-1)
                .build();

        log.info(replyService.getListOfBoard(bno, pageRequestDTO));

    }

}
