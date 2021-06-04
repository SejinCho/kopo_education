package kr.ac.kopo.ui;

import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.MemberVO;

public class LoginUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		
		
		boolean bool = false;
		
		do {
			String id = getString("아이디를 입력하세요. : ");
			String passwd = getString("비밀번호를 입력하세요 : ") ;
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			MemberVO member = memberService.getMemberInfo(map);
			
			if(member.getId() == null) {
				System.out.println("아이디가 존재하지 않습니다.");
				continue;
			}
			if(member.getPassWd().equals(passwd)) {
				System.out.println("로그인 성공");
				SessionFactory.setSessionInstance(member);
				bool = true;
			}else {
				System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
			}
			
		}while(!bool);
		
	}
}
