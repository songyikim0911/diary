package org.ssong.diary.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ssong.diary.dto.BoardDTO;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){

        IntStream.rangeClosed(1,200).forEach(i->{
            BoardDTO boardDTO = BoardDTO.builder()
                    .title("Title...."+i)
                    .content("content..."+i)
                    .writer("writer..."+i)
                    .build();

            Long bno = boardService.register(boardDTO);
            System.out.println("BNO:"+bno);

        });

    }

}