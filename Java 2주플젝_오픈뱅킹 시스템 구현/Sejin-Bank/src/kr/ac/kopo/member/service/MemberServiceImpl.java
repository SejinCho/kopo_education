package kr.ac.kopo.member.service;

import java.util.Map;

import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	MemberDAOImpl dao = new MemberDAOImpl();
	
	@Override
	public MemberVO loginCheck(Map<String, String> map) {
		MemberVO member = new MemberVO();
		if(map.get("type").equals("kakao")) {
			member = dao.selectOne(map.get("kakao_id"));
		}else {
			member = dao.loginCheck(map.get("user_id"), map.get("user_password"));
		}
		return member;
	}
	
	@Override
	public boolean join(MemberVO member) {
		return dao.insertOne(member);
	}
	
	//아이디 중복체크
	@Override
	public boolean vaildUserId(String user_id) {
		return dao.vaildUserId(user_id);
	}
	
	//주민번호 가져오기
	@Override
	public String getJuminNo(String id) {
		return dao.getJuminNo(id);
	}
}
