package kr.ac.kopo.member.dao;

import kr.ac.kopo.member.vo.MemberVO;

public interface MemberDAO {
	
	//로그인
	public MemberVO loginCheck(String user_id, String user_password);
	public MemberVO selectOne(String kakao_id); //카카오 아이디로 
	
	
	public boolean insertOne(MemberVO member);
	public boolean vaildUserId(String user_id); //user_id 중복확인
	
	//정보 가져오기
	//주민번호 가져오기
	public String getJuminNo(String id);
	
}
