package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MemberVO;

public class ManageMemberUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		System.out.println("===========================");
		System.out.println("        전체 회원 정보       ");
		System.out.println("===========================");
		
		List<MemberVO> voList = managerService.getMemberInfo("all");
		for(MemberVO vo : voList) {
			System.out.print("아이디 : " + vo.getId());
			System.out.print(", 이름 : " + vo.getName());
			System.out.print(", 핸드폰 : " + vo.getPhone());
			System.out.print(", 주소 : " + vo.getAddr());
			System.out.print(", 성별 : ");
			if(vo.getGender() == 1) {
				System.out.print("남자");
			}else {
				System.out.print("여자");
			}
			System.out.println(", 회원가입 날짜 : " + vo.getJoin_date());
		}
		
		String str = getString("회원을 검색하시려면 아이디를 입력(메인으로 돌아가려면 exit 입력) : ");
		if(str.equalsIgnoreCase("exit")) {
			return;
		}else {
			System.out.println("===========================");
			System.out.println(str+ "으로 검색된 회원 정보");
			System.out.println("===========================");
			List<MemberVO> voSearch = managerService.getMemberInfo(str);
			if(voSearch != null) {
				for(MemberVO vo : voSearch) {
					System.out.print("아이디 : " + vo.getId());
					System.out.print(", 이름 : " + vo.getName());
					System.out.print(", 핸드폰 : " + vo.getPhone());
					System.out.print(", 주소 : " + vo.getAddr());
					System.out.print(", 성별 : ");
					if(vo.getGender() == 1) {
						System.out.print("남자");
					}else {
						System.out.print("여자");
					}
					System.out.println(", 회원가입 날짜 : " + vo.getJoin_date());
				}
			}
			
		}
	}
}
