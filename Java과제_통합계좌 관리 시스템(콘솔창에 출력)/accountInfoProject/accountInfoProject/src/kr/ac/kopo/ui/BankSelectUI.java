package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class BankSelectUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		DecimalFormat format = new DecimalFormat("###,###");
		System.out.println("========================================");
		System.out.println("                ���ະ �˻�  ");
		System.out.println("========================================");
		String bank = getString("�˻��� ������ �Է����ּ���.(�ϳ�, ����, ���, ���, ����) : ");
		
		Map<String, List<AccountInfoVO>> map = accountInfoService.accountSelect(bank,"me");
		List<AccountInfoVO> voList = map.get(bank);
		
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("           "+ bank +" ����");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
			}
		}else {
			System.out.println("========================================");
			System.out.println("          "+ bank +"������ ���°� �������� �ʽ��ϴ�.");
			System.out.println("========================================");
		}
		
	}
}
