package com.khit.study.service;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.khit.study.entity.Board;
import com.khit.study.repository.BoardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {
	
	private BoardRepository boardRepository;

	public void save(Board board) {
		
		boardRepository.save(board);
	}

	public List<Board> findAll() {
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public Board findById(int id) {
		//1건 검색 - findById().get()
		return boardRepository.findById(id).get();
	}

	public void delete(int id) {
		//1건 검색 - deleteById(id)
		boardRepository.deleteById(id);
		
	}  

	public void update(Board board) {
		//수정일은 직접 생성해줘야함	
		board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		boardRepository.save(board);
	}


}
