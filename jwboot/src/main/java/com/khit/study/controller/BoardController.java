package com.khit.study.controller;




import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khit.study.entity.BoardVO;
import com.khit.study.service.BoardService;

import lombok.AllArgsConstructor;

//문자열을 반환하는 어노테이션
//위치 : 메서드에 위치함 - @ResponseBody, @ResponseEntity와 비슷함


@AllArgsConstructor
@RestController
public class BoardController {

	private BoardService boardService;
	
	@GetMapping("/greeting")
	public String sayHello(String name) {
		return "hello" + name;		//문자열 rest가 붙어있기때문에
	}
	
	//객체 데이터를 브라우저에 보내줌
	@GetMapping("/board/detail")
	public BoardVO getBoard() {
		//게시글 1건 생성
		BoardVO board = boardService.getBoard();
		return board;
	}
	
	@GetMapping("/board/list")
	public List<BoardVO> gerBoardList(){
		List<BoardVO> boardlist = boardService.gerBoardList();
		return boardlist;
	}
	
}
