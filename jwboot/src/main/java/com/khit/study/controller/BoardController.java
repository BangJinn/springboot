package com.khit.study.controller;




import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khit.study.entity.Board;
import com.khit.study.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@Slf4j
@Controller
public class BoardController {

	private BoardService boardService;
	
	@GetMapping("/board/write")
	public String writeForm() {
		return "/board/write";
	}
	//글쓰기 처리
	@PostMapping("/board/write")
	public String write(@ModelAttribute Board board) {
		log.info("board: " + board);
		boardService.save(board);
		return "redirect:/board/";
	}
	
	//글 목록
	@GetMapping("/board/")
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.findAll();
		model.addAttribute("boardList", boardList);
		return "/board/list";
	}
	
	//글 상세보기
	@GetMapping("/board")
	public String getBoard(@RequestParam("id") int id,
			Model model) {
		Board board = boardService.findById(id);
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	//글 삭제하기
	@GetMapping("/board/delete")
	public String deleteBoard(@RequestParam("id") int id) {
		boardService.delete(id);
		return "redirect:/board/";
	}
	
	//글 수정하기
	@GetMapping("/board/update")
	public String updateBoard(@RequestParam("id") int id,
			Model model) {
		Board board = boardService.findById(id);
		model.addAttribute("board", board);
		return "/board/update";
	}
	
	//수정 처리
	@PostMapping("/board/update")
	public String update(@ModelAttribute Board board) {
		boardService.update(board);
		return "redirect:/board?id=" + board.getId();
	}
	
}
