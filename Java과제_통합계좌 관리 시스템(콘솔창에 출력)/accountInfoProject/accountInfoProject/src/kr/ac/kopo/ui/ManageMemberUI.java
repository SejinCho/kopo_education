package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MemberVO;

public class ManageMemberUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		System.out.println("===========================");
		System.out.println("        ��ü ȸ�� ����       ");
		System.out.println("===========================");
		
		List<MemberVO> voList = managerService.getMemberInfo("all");
		for(MemberVO vo : voList) {
			System.out.print("���̵� : " + vo.getId());
			System.out.print(", �̸� : " + vo.getName());
			System.out.print(", �ڵ��� : " + vo.getPhone());
			System.out.print(", �ּ� : " + vo.getAddr());
			System.out.print(", ���� : ");
			if(vo.getGender() == 1) {
				System.out.print("����");
			}else {
				System.out.print("����");
			}
			System.out.println(", ȸ������ ��¥ : " + vo.getJoin_date());
		}
		
		String str = getString("ȸ���� �˻��Ͻ÷��� ���̵� �Է�(�������� ���ư����� exit �Է�) : ");
		if(str.equalsIgnoreCase("exit")) {
			return;
		}else {
			System.out.println("===========================");
			System.out.println(str+ "���� �˻��� ȸ�� ����");
			System.out.println("===========================");
			List<MemberVO> voSearch = managerService.getMemberInfo(str);
			if(voSearch != null) {
				for(MemberVO vo : voSearch) {
					System.out.print("���̵� : " + vo.getId());
					System.out.print(", �̸� : " + vo.getName());
					System.out.print(", �ڵ��� : " + vo.getPhone());
					System.out.print(", �ּ� : " + vo.getAddr());
					System.out.print(", ���� : ");
					if(vo.getGender() == 1) {
						System.out.print("����");
					}else {
						System.out.print("����");
					}
					System.out.println(", ȸ������ ��¥ : " + vo.getJoin_date());
				}
			}
			
		}
	}
}
