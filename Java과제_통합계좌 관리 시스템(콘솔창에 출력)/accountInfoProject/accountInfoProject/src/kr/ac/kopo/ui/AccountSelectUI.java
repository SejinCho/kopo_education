package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class AccountSelectUI extends BaseUI{ //���¹�ȣ �˻��ؼ� �������� ��������
	
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		String account_number = "";
		boolean account_number_bool = false;
		do {
			account_number = getString("���¹�ȣ�� �Է����ּ���('-'�� �Է����� ������!) : ");
			account_number_bool = accountInfoService.isValid_account_number(account_number);
		}while(! account_number_bool);
		
		Map<String, AccountInfoVO> map = accountInfoService.getAccountselect(account_number,"me");
		
		if(map == null) {
			System.out.println("===========================================");
			System.out.println("   *********���°� �������� �ʽ��ϴ�.*********   ");
			System.out.println("===========================================");
			System.out.println();
		}else {
			Iterator<String> keys = map.keySet().iterator();
			String key = "";
			while(keys.hasNext()) {
				key = keys.next();
			}
			System.out.println("===========================================");
			System.out.println(key +"������ " + account_number + " ���� ����");
			System.out.println("===========================================");
			System.out.print("��Ī : " + map.get(key).getNickname());
			System.out.print(", ���¹�ȣ : " + map.get(key).getAccount_number());
			System.out.print(", �ܾ� : " + format.format(map.get(key).getBalance()));
			System.out.print(", ��� ��¥ : " + map.get(key).getRegistration_date());
			System.out.println();
		}
		
	}

}
