package kr.ac.kopo.library.member;

import java.util.List;

public interface Member {
	public boolean fileExists();
	
	//로그인
	public int login(String id, String pw); //로그인 체크
	public MemberDTO getMemInfo(String id); //회원정보 가져오기
	
	//회원가입
	public boolean isValidEmail(String email); //이메일 형식 확인
	public boolean emailCheck(String newEmail); //이미 가입된 이메일이 아닌지 확인
	public void mailSend(String email); //이메일 인증번호 전송
	public boolean emailAuth(int inputNum); //인증번호 일치 여부
	public boolean idCheck(String id); //아이디 중복 확인 
	public boolean confirmPW(String pw, String repw); //비번 재확인
	public boolean memInfoWrite(String name, String email, String id, String pw, int gender); //회원 정보 저장
	
	public List<MemberDTO> getMemInfoList(); //회원정보 가져오기
	public void myInfoPrint(String id); //특정 회원 정보 출력
	public boolean updatePW(String id, String updatePW); //비밀번호 수정
	
	
}
