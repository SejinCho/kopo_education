package kr.ac.kopo.service;

import java.util.Map;
import java.util.regex.Pattern;

import kr.ac.kopo.dao.MemberDAO;
import kr.ac.kopo.vo.MemberVO;

public class MemberService {
	private MemberDAO memberDao;
	
	public MemberService() {
		memberDao = new MemberDAO();
	}
	
	//회원가입
	public boolean join(MemberVO member) {
		boolean result = memberDao.join(member);
		return result;
	}
	
	//아이디 중복 검사
	public boolean isValid_Id(String id) {
		boolean result = memberDao.isValid_Id(id);
		
		return result;
	}
	
	//핸드폰 번호 중복 검사 & 형식
	public int isValid_Phone(String phone) {
		int result = 0; // 0 = 형식이 올바르지 않음, 1 = 중복, 2 = 유효성 통과 
		
		//1. 형식 검사
		if(! Pattern.matches("(01[016789])-(\\d{3,4})-(\\d{4})", phone)) {
			return result;
		}
		
		//2. 중복 검사
		if(memberDao.isValid_Phone(phone)) {
			result = 2;
		}else {
			result = 1;
		}
		
		return result;
	}
	
	//id나 index로 member 1명 정보 가져오기
	public MemberVO getMemberInfo(Map<String, String> map) { //type이 id or member_index
		MemberVO member= memberDao.getMemberInfo(map);
		return member;
	}
	
	
	
	
}
