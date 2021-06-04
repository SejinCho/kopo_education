package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class AccountDeleteUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		//��ü ���� ��ȸ
		IAccountInfoUI ui = new AccountAllSelectUI();
		ui.contents();
		System.out.println();
		
		String bank = getString("������ ������ ������ �Է����ּ���(�ϳ�, ����, ���, ����, ���)  : ");
		Map<String, List<AccountInfoVO>> map = accountInfoService.accountSelect(bank,"me");
		
		List<AccountInfoVO> voList = new ArrayList<>();
		voList = map.get(bank);
		
		if(voList != null) {
			System.out.println("========================================");
			System.out.println("            " + bank  + " ����");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		String account_number = getString("������ ���¹�ȣ�� �Է��ϼ��� : ");
		boolean deleteBool = accountInfoService.accountDelete(bank, account_number);
		if(deleteBool) {
			System.out.println("���� ���� ����");
		}else {
			System.out.println("���� ���� ����");
		}
	}
}
