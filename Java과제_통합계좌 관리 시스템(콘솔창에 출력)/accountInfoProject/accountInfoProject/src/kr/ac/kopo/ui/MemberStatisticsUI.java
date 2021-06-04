package kr.ac.kopo.ui;

import java.util.Map;

public class MemberStatisticsUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		System.out.println("=============================");
		System.out.println("         통계 정보 ");
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
		System.out.println("   [ 회원 남, 여 통계  ]");
		System.out.println();
		System.out.println("남자 회원 수 : " + num1 + "명");
		System.out.println("여자 회원 수 : " + num2 + "명");
		System.out.println();
		
		
		
	}
}
