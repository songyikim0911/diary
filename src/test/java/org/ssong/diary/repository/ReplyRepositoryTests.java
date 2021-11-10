package org.ssong.diary.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ssong.diary.entity.Board;
import org.ssong.diary.entity.Reply;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReplyRepository replyRepository;


    @Test
    public void testByBno(){
        Long bno = 200L;
        List<Reply> replyList = replyRepository.findReplyByBoard_BnoOrderByRno(bno);
        replyList.forEach(reply->log.info(reply));
    }



    @Test
    public void insertTest(){

        IntStream.rangeClosed(1, 200).forEach(i->{

            Long bno = (long)(200 - (i % 5));

            int replyCount = (i % 5);

            IntStream.rangeClosed(0, replyCount).forEach(j->{

                Board board = Board.builder().bno(bno).build();

                Reply reply = Reply.builder()
                        .replyText("Reply..."+i)
                        .replyer("replyer..."+j)
                        .board(board)
                        .build();
                replyRepository.save(reply);
            });//inner loop

        });//outer loop

    }


}
