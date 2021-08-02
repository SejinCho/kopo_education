package kr.ac.kopo.member.service;

import java.util.Map;

import kr.ac.kopo.member.vo.MemberVO;

public interface MemberService {
	public MemberVO loginCheck(Map<String, String> map); //카카오 아이디로 유저 정보 가져오기
	public boolean join(MemberVO member);
	public boolean vaildUserId(String user_id); //유저 아이디 중복체크
	public String getJuminNo(String id);
}
