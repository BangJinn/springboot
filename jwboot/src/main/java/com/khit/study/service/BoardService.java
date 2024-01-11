package com.khit.study.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.khit.study.entity.BoardVO;

@Controller
public class BoardService {

	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setId(1);
		board.setTitle("제목");
		board.setWriter("글쓴이");
		board.setContent("내용임");
		board.setCreatedDate(new Date());
		return board;
	}
	
	//목록 보기
	public List<BoardVO> gerBoardList(){
		List<BoardVO> boardList = new ArrayList<>();
		for(int i = 1; i<=10; i++) {
			BoardVO board = new BoardVO();
			board.setId(i);
			board.setTitle("제목" + i);
			board.setWriter("글쓴이");
			board.setContent(i + "내용임");
			board.setCreatedDate(new Date());
			boardList.add(board);
		}
		return boardList;
	}
	
}
