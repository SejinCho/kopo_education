package kr.ac.kopo.ui;

import java.util.Map;

public class MemberStatisticsUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		System.out.println("=============================");
		System.out.println("         ��� ���� ");
		System.out.println("=============================");
		System.out.println();
		
		Map<Integer, Integer> map = managerService.getStatisticsGender();
		int num1 = 0;
		int num2 = 0;
		if(map.get(1) != null) {
			num1 = map.get(1);
		}
		if(map.get(2)!= null) {
			num2 = map.get(2);
		}
		System.out.println("   [ ȸ�� ��, �� ���  ]");
		System.out.println();
		System.out.println("���� ȸ�� �� : " + num1 + "��");
		System.out.println("���� ȸ�� �� : " + num2 + "��");
		System.out.println();
		
		
		
	}
}
