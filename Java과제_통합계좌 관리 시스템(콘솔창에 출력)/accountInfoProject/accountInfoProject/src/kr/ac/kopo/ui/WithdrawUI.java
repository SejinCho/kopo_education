package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class WithdrawUI extends BaseUI{ //��� 
	
	@Override
	public void contents() throws Exception { 
		DecimalFormat format = new DecimalFormat("###,###");
		boolean accountInfoBool = false;
		String bank = "";
		Map<String, Object> map = new HashMap<String, Object>();
		AccountInfoVO vo = null;
		
		do {
			
			//��ü ���� ��ȸ
			IAccountInfoUI ui = new AccountAllSelectUI();
			ui.contents();
			System.out.println();
			
			bank = getString("����� ������ �Է��ϼ���.(�ϳ�, ����, ���, ���, ����) : ");
			String account_number = getString("����� ���¸� �Է��ϼ���.('-'�� �Է����� ������.) : ");
			map.put("bank", bank);
			
			//���� ���� ��������
			vo = accountInfoService.oneAccountInfo(bank, account_number,"me");
			
			if(vo == null) {
				System.out.println("���¹�ȣ�� �߸� �Է��߽��ϴ�.");
				continue;
			}else {
				String account_passwd = getString("������ ��й�ȣ�� �Է��ϼ��� : ");
				if(! account_passwd.equals(vo.getAccount_passwd())) {
					System.out.println("���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					continue;
				}
				
				System.out.println("===========================================");
				System.out.println(bank +"������ " + account_number + " ���� ����");
				System.out.println("===========================================");
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
				map.put("account_index", vo.getAccount_index());
				
				accountInfoBool = true;
			}
		}while(! accountInfoBool);
		
		int money = 0;
		boolean moneyBool = false;
		do {
			money = getInt("�󸶸� ����Ͻðڽ��ϱ�? : ");
			if(money > vo.getBalance()) {
				System.out.println("�ܾ׺��� ���� ����� �� �����ϴ�. ���� �ܾ��� " + format.format(vo.getBalance()) + "�� �Դϴ�.");
				continue;
			}
			moneyBool = true;
			map.put("money", -money);
		}while(! moneyBool);
		
		
		boolean depositResult = transferInfoService.balanceUpdate(map);
		if(depositResult) {
			System.out.println("��� ����");
		}else {
			System.out.println("��� ����");
		}
		
	}
}
