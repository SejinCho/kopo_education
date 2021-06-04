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
			
			
			//���̵�(�ߺ��˻�)
			String id = "";
			do {
				id = getString("���̵� �Է��ϼ���. : "); 
				idCheckResult = memberService.isValid_Id(id);
				if(! idCheckResult) {
					System.out.println("���̵� �����մϴ�.");
				}
			}while(! idCheckResult);
			
			//��й�ȣ
			String passwd = "";
			String rePasswd = "";
			do {
				passwd = getString("��й�ȣ�� �Է��ϼ���. : ");
				rePasswd = getString("��й�ȣ�� �ٽ� �Է��ϼ���. : ");
				if(! passwd.equals(rePasswd)) {
					System.out.println("��й�ȣ�� �߸� �Է��߽��ϴ�.");
				}
			}while(! passwd.equals(rePasswd));
			
			
			//�̸�
			String name = getString("�̸��� �Է��ϼ��� : ");
			
			//�ڵ��� ��ȣ(�ߺ��˻�)
			int phoneCheckResult = 0;
			String phone = "";
			do {
				phone = getString("�ڵ��� ��ȣ�� �Է��ϼ���(ex.010-1234-4567) : ");
				phoneCheckResult = memberService.isValid_Phone(phone);
				if(phoneCheckResult == 0) {
					System.out.println("�ڵ��� ��ȣ�� ������ �ùٸ��� �ʽ��ϴ�.");
				}else if(phoneCheckResult == 1) {
					System.out.println("�ڵ��� ��ȣ�� �ߺ��Դϴ�.");
				}
			}while(!(phoneCheckResult == 2));
			
			//�ּ� 
			String addr = getString("�ּҸ� �Է��ϼ��� : ");
			
			//���� �Է� (1.��, 2.��)
			int gender = getInt("������ �Է��ϼ���(1. ��, 2. ��) : ");
			
			member.setId(id);
			member.setPassWd(passwd);
			member.setName(name);
			member.setPhone(phone);
			member.setAddr(addr);
			member.setGender(gender);
			
			result = memberService.join(member);
			System.out.println(result ? "ȸ������ �Ϸ�" : "ȸ������ ����");
			System.out.println();
			
			//�ش� ���� index �����ͼ� ����(?)�� ����
			Map<String, String> map = new HashMap<>();
			map.put("id", id);
			member = memberService.getMemberInfo(map);
			
			SessionFactory.setSessionInstance(member);
			
		}while(! result);
		
	}
}
