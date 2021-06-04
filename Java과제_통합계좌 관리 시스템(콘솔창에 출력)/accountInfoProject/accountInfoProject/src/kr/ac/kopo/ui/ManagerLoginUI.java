package kr.ac.kopo.ui;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.ManagerVO;

public class ManagerLoginUI extends BaseUI{
	@Override
	public void contents() throws Exception { //관리자 로그인
		System.out.println("====================================");
		System.out.println("             관리자 로그인 ");
		System.out.println("====================================");
		
		do {
			String manager_id = getString("아이디를 입력하세요 : ");
			String manager_passwd = getString("비밀번호를 입력허세요 : ");
			ManagerVO vo = managerService.managerLogin(manager_id);
			if(vo == null) {
				System.out.println("아이디가 존재하지 않습니다.");
				continue;
			}
			if(! vo.getManager_passwd().equals(manager_passwd)) {
				System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
				continue;
			}else {
				System.out.println("로그인 성공");
				SessionFactory.setSessionInstance(vo);
				AccountInfoUI ui = new AccountInfoUI();
				ui.managerContents();
				break;
			}
		}while(true);
		
		
	}
}
