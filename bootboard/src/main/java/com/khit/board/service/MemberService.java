package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {
		//db 안으로 저장(entity를 저장해야함)
		//dto를 entity로 변환 메서드 필요
		Member member = Member.toSaveEntity(memberDTO);

		memberRepository.save(member);
	}

	public List<MemberDTO> findAll() {
		//db에서 member 엔티티를 꺼내와서 
		List<Member> memberList = memberRepository.findAll();
		//변환 메서드가 필요
		//member를 담을 빈 dto 리스트를 생성
		List<MemberDTO> memberDTOList = new ArrayList<>(); 
		
		for(Member member : memberList) { //memberList를 반복하면서 변환함
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			memberDTOList.add(memberDTO);
		}
		
		//컨트롤러에 DTOList로 보냄
		return memberDTOList;
	}

	public MemberDTO findById(Long id) {
		//db에서 member 1건 꺼내옴 - findById(id).get()
		//id가 없을 때 오류 발생 처리
		Optional <Member> member = memberRepository.findById(id);
		if(member.isPresent()) {
		//entity -> dto 변환
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
		return memberDTO;
	}else {
		throw new BootBoardException("아이디가 없어용");	
	}
}

	public void deleteById(Long id) {
		//삭제 - deleteById(id)
		memberRepository.deleteById(id);
	}

	public MemberDTO login(MemberDTO memberDTO) {
		// 1. 이메일로 회원 조회(이메일과 비밀번호를 비교)
		Optional<Member> memberEmail =
				memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
		if(memberEmail.isPresent()) { 
			//조회 결과 이메일이 있음 - 1건 가져옴
			Member member = memberEmail.get();
			//비밀 번호 일치
			if(member.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				//entity -> dto로 변환
				MemberDTO dto = MemberDTO.toSaveDTO(member);
				return dto;
			}else {
				//비밀 번호 불일치
				return null;
			}
		}else {
			return null;
		}
	}

	public MemberDTO findByMemberEmail(String email) {
		//db에서 이메일로 검색한 회원 객체 가져오고
		Member member = memberRepository.findByMemberEmail(email).get();
		//회원 객체(엔티티)를 dto로 변환
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
		return memberDTO;
	}

	public void update(MemberDTO memberDTO) {
		//save()가 가입, 수정되는데 가입할때는 id가 없음, 수정할때는 id가 있음
		Member member = Member.toUpdateEntity(memberDTO);
		//id가 있는 엔티티의 메서드 필요함
		memberRepository.save(member);
	}

	public String checkEmail(String memberEmail) {
		//db에 있는 이메일을 조회해서 있으면 "OK" 아니면 "NO"를 보냄
		Optional<Member> findMember = memberRepository.findByMemberEmail(memberEmail);
		if(findMember.isEmpty()) {	//db에 회원이 없으면 가입 OK
			return "OK";
		}else {						//그렇지 않으면 NO
			return "NO";
		}
	}
}