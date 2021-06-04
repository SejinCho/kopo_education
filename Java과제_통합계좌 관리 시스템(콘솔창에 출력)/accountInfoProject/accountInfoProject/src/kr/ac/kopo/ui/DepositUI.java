package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class DepositUI extends BaseUI { //�Ա�
	@Override
	public void contents() throws Exception{
		DecimalFormat format = new DecimalFormat("###,###");
		boolean accountInfoBool = false;
		String bank = "";
		Map<String, Object> map = new HashMap<String, Object>();
		
		do {
			//��ü ���� ��ȸ
			IAccountInfoUI ui = new AccountAllSelectUI();
			ui.contents();
			System.out.println();
			
			bank = getString("�Ա��� ������ �Է��ϼ���.(�ϳ�, ����, ���, ���, ����) : ");
			String account_number = getString("�Ա��� ���¸� �Է��ϼ���.('-'�� �Է����� ������.) : ");
			map.put("bank", bank);
			
			//���� ���� ��������
			AccountInfoVO  vo = accountInfoService.oneAccountInfo(bank, account_number, "me");
			
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
		
		int money = getInt("�󸶸� �Ա��Ͻðڽ��ϱ�? : ");
		map.put("money", money);
		
		
		boolean depositResult = transferInfoService.balanceUpdate(map);
		if(depositResult) {
			System.out.println("�Ա� ����");
		}else {
			System.out.println("�Ա� ����");
		}
		
	}
	
}
