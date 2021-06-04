package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class AccountAllSelectUI extends BaseUI{//��ü ������ȸ
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		System.out.println("========================================");
		System.out.println("            ��ü ���� ��ȸ");
		
		
		Map<String, List<AccountInfoVO>> map = accountInfoService.accountSelect("all","me");
		List<AccountInfoVO> voList = new ArrayList<AccountInfoVO>();
		
		if(map.size() == 0) {
			System.out.println("========================================");
			System.out.println("         ��ϵ� ���°� �������� �ʽ��ϴ�.");
			System.out.println("========================================");
		}
		
		//�ϳ�����
		voList = map.get("�ϳ�");
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            �ϳ� ����");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		//�������
		voList = map.get("���");
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            ��� ����");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		//��������
		voList = map.get("����");
		
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            ���� ����");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		//�������
		voList = map.get("���");
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            ��� ����");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		//��������
		voList = map.get("����");
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            ���� ����");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("��Ī : " + vo.getNickname());
				System.out.print(", ���¹�ȣ : " + vo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(vo.getBalance()));
				System.out.print(", ��� ��¥ : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
	}
	
}
