package com.khit.board.entity;


import java.time.LocalDateTime;

import com.khit.board.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "tbl_board")
@Entity
public class Board extends BaseEntity{

	@Id		//프라이머리 키
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String boardTitle;
	@Column
	private String boardWriter;
	@Column
	private String boardContent;
	@Column
	private Integer boardHits;
	
	
	//dto를 entity로 변환하는 정적 메서드
	public static Board toSaveEntity(BoardDTO boaraDTO) {
		Board board = Board.builder()
				.id(boaraDTO.getId())
				.boardTitle(boaraDTO.getBoardTitle())
				.boardWriter(boaraDTO.getBoardWriter())
				.boardContent(boaraDTO.getBoardContent())
				.boardHits(0)
				.build();
		return board;
	}
	
	public static Board toUpdateEntity(BoardDTO boaraDTO) {
		Board board = Board.builder()
				.id(boaraDTO.getId())
				.boardTitle(boaraDTO.getBoardTitle())
				.boardWriter(boaraDTO.getBoardWriter())
				.boardContent(boaraDTO.getBoardContent())
				.boardHits(boaraDTO.getBoardHits())
				.build();
		return board;
	}
}
