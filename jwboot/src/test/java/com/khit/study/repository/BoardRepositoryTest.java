package com.khit.study.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	//게시글 생성
	
//	@Test
//	public void insertBoard() {
//		Board board = new Board();
//		board.setTitle("가입 인사");
//		board.setWriter("고병진");
//		board.setContent("하이ㅋㅋ");
//		board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//		//db에 저장
		//boardRepository.save(board);
	
		
		
//		Board board = Board.builder()
//				.title("가입인사2")
//				.writer("김병진")
//				.content("왜안돼")
//			.createdDate(new Timestamp(System.currentTimeMillis()))
//			.build();
//				
		
	
	
	  @Test 
	  public void getBoardList() { 
	   List<Board> boardList = boardRepository.findAll();
//	   		for(Board board : boardList) 
//	   			log.info("" + board.toString()); 
	   
	   //람다식
	   boardList.forEach(board -> log.info(board.toString()));
	   
   		}
	  
	  @Test
	  public void getBoard() {
		  //findById()와 get()을 사용
		  Board board = boardRepository.findById(2).get();
		  log.info(board.toString());
		  
	  }
	 //수정하기
	  @Test
	  public void updateBoard() {
		  Board board = boardRepository.findById(1).get();
		  board.setTitle("수정");
		  board.setContent("수정아..");
		  
		  boardRepository.save(board);
	  }
	  
	  //
	  @Test
	  public void deleteBoard() {
		  boardRepository.deleteById(2);
	  }
}
