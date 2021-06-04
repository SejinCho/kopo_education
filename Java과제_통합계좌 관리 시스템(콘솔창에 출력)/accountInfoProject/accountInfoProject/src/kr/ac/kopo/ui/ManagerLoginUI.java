package kr.ac.kopo.ui;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.ManagerVO;

public class ManagerLoginUI extends BaseUI{
	@Override
	public void contents() throws Exception { //������ �α���
		System.out.println("====================================");
		System.out.println("             ������ �α��� ");
		System.out.println("====================================");
		
		do {
			String manager_id = getString("���̵� �Է��ϼ��� : ");
			String manager_passwd = getString("��й�ȣ�� �Է��㼼�� : ");
			ManagerVO vo = managerService.managerLogin(manager_id);
			if(vo == null) {
				System.out.println("���̵� �������� �ʽ��ϴ�.");
				continue;
			}
			if(! vo.getManager_passwd().equals(manager_passwd)) {
				System.out.println("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				continue;
			}else {
				System.out.println("�α��� ����");
				SessionFactory.setSessionInstance(vo);
				AccountInfoUI ui = new AccountInfoUI();
				ui.managerContents();
				break;
			}
		}while(true);
		
		
	}
}
