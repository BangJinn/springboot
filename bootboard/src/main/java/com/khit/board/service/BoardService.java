package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO) {
		//dto를 ->entity로 변환
		Board board = Board.toSaveEntity(boardDTO);
		
		boardRepository.save(board);
		
	}

	public List<BoardDTO> findAll() {
		//db에서 entity list를 가져옴
		List<Board> boardList = boardRepository.findAll();
		//entity를 dto로 변환하기 위해 빈 어레이 리스트를 생성
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		for(Board board : boardList) {
			//entity를 dto로 변환
			BoardDTO boardDTO = BoardDTO.tosaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}

	public BoardDTO findById(Long id) {
		Optional<Board> findBoard = boardRepository.findById(id);
		if(findBoard.isPresent()) {	//검색한 게시글이 있으면 dto를 컨트롤러로 변환함
			BoardDTO boardDTO = BoardDTO.tosaveDTO(findBoard.get());
			return boardDTO;
		}else {	//게시글이 없으면 에러 처리함
			throw new BootBoardException("게시글 못찾아용");
		}
		
		
	}
	@Transactional		//컨트롤러 작업이 2개 이상이면 사용함
	public void updateHits(Long id) {
		//이 메서드를 boardRepository에 생성
		boardRepository.updateHits(id);
	}
	
	//글삭제
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}
	
	//글수정
	public void update(BoardDTO boardDTO) {
		//save() - 삽입(id가 없고), 수정(id가 있음)
		//dto -> entity
		Board board = Board.toUpdateEntity(boardDTO);
		boardRepository.save(board); 
	}



	
}