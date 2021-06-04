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
			String id = getString("���̵� �Է��ϼ���. : ");
			String passwd = getString("��й�ȣ�� �Է��ϼ��� : ") ;
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			MemberVO member = memberService.getMemberInfo(map);
			
			if(member.getId() == null) {
				System.out.println("���̵� �������� �ʽ��ϴ�.");
				continue;
			}
			if(member.getPassWd().equals(passwd)) {
				System.out.println("�α��� ����");
				SessionFactory.setSessionInstance(member);
				bool = true;
			}else {
				System.out.println("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
			
		}while(!bool);
		
	}
}
