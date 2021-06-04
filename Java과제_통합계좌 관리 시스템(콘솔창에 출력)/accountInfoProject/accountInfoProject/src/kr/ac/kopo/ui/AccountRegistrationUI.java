package kr.ac.kopo.ui;

import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.AccountInfoVO;

public class AccountRegistrationUI extends BaseUI { //���µ��
	@Override
	public void contents() throws Exception {
		
		String bank = getString("������ �����ϼ���.(�ϳ�, ����, ���, ����, ���) : ");
		
		AccountInfoVO info = new AccountInfoVO();
		Map<String, AccountInfoVO> map = new HashMap<>();
		boolean account_number_boool = false;
		String account_number = "";
		
		if(bank.equals("�ϳ�")) {
			info.setAccount_type(1); //1. ��� 2. ���� ����
		}
		
		do {
			account_number = getString("���¹�ȣ�� �Է��ϼ���(-�� �Է� ����) : ");
			
			account_number_boool = accountInfoService.isValid_account_number(account_number);
			if(! account_number_boool) {
				System.out.println("���� ��ȣ�� '-'�� �����ϰ� �Է��մϴ�.");
			}
		}while(! account_number_boool);
		
		boolean account_passwd_bool = false;
		String account_passwd = "";
		
		do {
			account_passwd = getString("���� ��й�ȣ�� �Է��ϼ���(4�ڸ� ����) : ");
			account_passwd_bool = accountInfoService.isValid_account_passwd(account_passwd);
			if(! account_passwd_bool) {
				System.out.println("��й�ȣ�� 4�ڸ� ���ڷ� �Է��մϴ�.");
			}
		}while(! account_passwd_bool);
		
		int money = (int) ((Math.random() * 10 + 1));
		money *= 1000;
		
		int balance = money;
		
		String nickname = getString("���� ��Ī�� �����ּ��� : ");
		String member_index = SessionFactory.getSessionInstance("member_index");
		
		info.setAccount_number(account_number);
		info.setAccount_passwd(account_passwd);
		info.setBalance(balance);
		info.setNickname(nickname);
		info.setMember_index(member_index);
		
		map.put(bank, info);
		
		boolean result = accountInfoService.account_registration(map);
		
		if(result) {
			System.out.println("���°� ��ϵǾ����ϴ�.");
		}else {
			System.out.println("���� ��� ����");
		}
		
		
	}

}
