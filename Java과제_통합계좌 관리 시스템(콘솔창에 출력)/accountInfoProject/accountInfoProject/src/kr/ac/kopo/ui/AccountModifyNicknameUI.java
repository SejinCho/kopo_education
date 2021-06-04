package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class AccountModifyNicknameUI extends BaseUI{ //��Ī ����
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		//��ü ���� ��ȸ
		IAccountInfoUI ui = new AccountAllSelectUI();
		ui.contents();
		System.out.println();
		
		String bank = getString("������ ������ �Է��ϼ���. (�ϳ�/���/����/���/����) : ");
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
		
		String account_number = getString("��Ī�� �ٲ� ���¹�ȣ�� �Է��ϼ��� : ");
		String nickname = getString("������ ��Ī�� �Է��ϼ��� : ");
		boolean modifyBool = accountInfoService.accountModifyNickname(bank, account_number, nickname);
		
		if(modifyBool) {
			System.out.println("���� �Ϸ�");
		}else {
			System.out.println("���� ����(���¹�ȣ�� ����� �Է����ּ���.)");
		}
	}
}
