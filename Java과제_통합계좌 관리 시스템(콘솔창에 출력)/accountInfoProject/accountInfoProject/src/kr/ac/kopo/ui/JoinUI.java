package kr.ac.kopo.ui;

import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.MemberVO;

public class JoinUI extends BaseUI{
	
	
	
	@Override
	public void contents() throws Exception {
		boolean result = false;
		
		do {
			boolean idCheckResult = false;
			MemberVO member = new MemberVO();
			
			
			//아이디(중복검사)
			String id = "";
			do {
				id = getString("아이디를 입력하세요. : "); 
				idCheckResult = memberService.isValid_Id(id);
				if(! idCheckResult) {
					System.out.println("아이디가 존재합니다.");
				}
			}while(! idCheckResult);
			
			//비밀번호
			String passwd = "";
			String rePasswd = "";
			do {
				passwd = getString("비밀번호를 입력하세요. : ");
				rePasswd = getString("비밀번호를 다시 입력하세요. : ");
				if(! passwd.equals(rePasswd)) {
					System.out.println("비밀번호를 잘못 입력했습니다.");
				}
			}while(! passwd.equals(rePasswd));
			
			
			//이름
			String name = getString("이름을 입력하세요 : ");
			
			//핸드폰 번호(중복검사)
			int phoneCheckResult = 0;
			String phone = "";
			do {
				phone = getString("핸드폰 번호를 입력하세요(ex.010-1234-4567) : ");
				phoneCheckResult = memberService.isValid_Phone(phone);
				if(phoneCheckResult == 0) {
					System.out.println("핸드폰 번호의 형식이 올바르지 않습니다.");
				}else if(phoneCheckResult == 1) {
					System.out.println("핸드폰 번호가 중복입니다.");
				}
			}while(!(phoneCheckResult == 2));
			
			//주소 
			String addr = getString("주소를 입력하세요 : ");
			
			//성별 입력 (1.남, 2.여)
			int gender = getInt("성별을 입력하세요(1. 남, 2. 여) : ");
			
			member.setId(id);
			member.setPassWd(passwd);
			member.setName(name);
			member.setPhone(phone);
			member.setAddr(addr);
			member.setGender(gender);
			
			result = memberService.join(member);
			System.out.println(result ? "회원가입 완료" : "회원가입 실패");
			System.out.println();
			
			//해당 유저 index 가져와서 세션(?)에 저장
			Map<String, String> map = new HashMap<>();
			map.put("id", id);
			member = memberService.getMemberInfo(map);
			
			SessionFactory.setSessionInstance(member);
			
		}while(! result);
		
	}
}
