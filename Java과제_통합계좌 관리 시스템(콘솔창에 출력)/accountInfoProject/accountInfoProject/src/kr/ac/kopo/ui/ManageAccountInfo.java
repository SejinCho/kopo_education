package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.List;

import kr.ac.kopo.vo.AccountInfoVO;

public class ManageAccountInfo extends BaseUI{
	@Override
	public void contents() throws Exception { //��ü ���� ����
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		System.out.println("==================================");
		System.out.println("           ��ü ���� ���� ");
		System.out.println("==================================");
		List<AccountInfoVO> voList = managerService.getAllAccountInfo();
		if(voList.size() == 0) {
			System.out.println("     ��ȸ�� ���°� �������� �ʽ��ϴ�.");
			System.out.println("==================================");
		}else {
			for(AccountInfoVO vo : voList) {
				System.out.print("���� : " + vo.getMember_index());
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		
	}
}
