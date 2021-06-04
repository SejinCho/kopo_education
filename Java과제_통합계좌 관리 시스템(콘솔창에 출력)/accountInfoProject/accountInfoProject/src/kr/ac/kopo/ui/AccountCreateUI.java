package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.AccountInfoVO;

//���� ����
public class AccountCreateUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		System.out.println("============================================");
		System.out.println("             �ϳ����� ���� ����               ");
		System.out.println("============================================");
		
		boolean createCheckBool = accountInfoService.account_create_check(SessionFactory.getSessionInstance("member_index"));
		if(! createCheckBool) {
			System.out.println("30�� ���� ���¸� �����߽��ϴ�. ���¸� ������ �� �����ϴ�.T_T");
			return;
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		AccountInfoVO createVo = new AccountInfoVO();
		
		createVo.setAccount_type(2); //���ο� ���� ����
		createVo.setMember_index(SessionFactory.getSessionInstance("member_index")); //��� �ε���
		
		boolean account_passwd_bool = false;
		String account_passwd = "";
		do {
			account_passwd = getString("������ ������ ��й�ȣ�� �Է��ϼ���.(4�ڸ�) : ");
			account_passwd_bool = accountInfoService.isValid_account_passwd(account_passwd);
			if(! account_passwd_bool) {
				System.out.println("��й�ȣ�� 4�ڸ��� �����ؾ� �մϴ�.");
			}
			String repasswd = getString("��й�ȣ�� �ٽ� �Է����ּ��� : ");
			if(!account_passwd.equals(repasswd)) {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				account_passwd_bool = false;
			}
			
		}while(!account_passwd_bool);
		
		createVo.setAccount_passwd(account_passwd); //��й�ȣ
		
		String nickname = getString("������ ������ ��Ī�� �����ϼ���. : ");
		createVo.setNickname(nickname); //���� ��Ī
		
		Random r = new Random();
		String account_number = "";
		
		//14�ڸ� ����
		for(int i = 0; i<14 ; ++i) {
			int t = r.nextInt(10) ;
			account_number += t;
		}
		
		createVo.setAccount_number(account_number); //���¹�ȣ
		
		
		
		System.out.println("****���� ���� ���� �� 1000�� �̻� �ԱݵǾ�� �մϴ�.****");
		
		
		String send_bank = "";
		String send_account_number = "";
		AccountInfoVO sendVo = new AccountInfoVO();
		
		AccountInfoVO vo = null;
		
		boolean accountInfoBool = false;
		do {
			//��ü ���� ��ȸ
			IAccountInfoUI ui = new AccountAllSelectUI();
			ui.contents();
			System.out.println();
			send_bank = getString("�۱��� ������ �Է��ϼ���.(�ϳ�, ����, ���, ���, ����) : ");
			
			boolean send_account_number_bool = false;
			do {
				send_account_number = getString("�۱��� ���¸� �Է��ϼ���.('-'�� �Է����� ������.) : ");
				send_account_number_bool = accountInfoService.isValid_account_number(send_account_number);
				if(! send_account_number_bool) {
					System.out.println("'-'�� �Է����� ������.");
				}
			}while(! send_account_number_bool);
			
			
			//���� ���� ��������
			vo = accountInfoService.oneAccountInfo(send_bank, send_account_number,"me");
			
			if(vo == null) {
				System.out.println("���¹�ȣ�� �߸� �Է��߽��ϴ�.");
				continue;
				
			}else {
				String send_account_passwd = getString("������ ��й�ȣ�� �Է��ϼ��� : ");
				if(! send_account_passwd.equals(vo.getAccount_passwd())) {
					System.out.println("���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					continue;
				}
				
				System.out.println("===========================================");
				System.out.println(send_bank +"������ " + send_account_number + " ���� ����");
				System.out.println("===========================================");
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
				
				
				accountInfoBool = true;
			}
		}while(! accountInfoBool);
		
		//�� �۱�
		int money = 0;
		boolean moneyBool = false;
		do {
			money = getInt("�󸶸� �۱��Ͻðڽ��ϱ�? : ");
			if(money > vo.getBalance()) {
				System.out.println("�ܾ׺��� ���� �۱��� �� �����ϴ�. ���� �ܾ��� " + format.format(vo.getBalance()) + "�� �Դϴ�.");
				continue;
			}else if(money <1000){
				System.out.println("1000������ ���� �۱��ϼž� �մϴ�.");
				continue;
			}
			moneyBool = true;
		}while(! moneyBool);
		
		sendVo.setAccount_index(vo.getAccount_index());
		sendVo.setBalance(-money);
		createVo.setBalance(money);
		
		map.put(send_bank, sendVo);
		map.put("create", createVo);
		map.put("send_bank", send_bank);
		
		boolean depositResult = accountInfoService.account_create(map);
		if(depositResult) {
			System.out.println("���� ���� ����");
		}else {
			System.out.println("���� ���� ����");
		}
		
		
	}	
}
