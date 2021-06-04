package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.List;

import kr.ac.kopo.vo.AccountInfoVO;

public class ManageAccountInfo extends BaseUI{
	@Override
	public void contents() throws Exception { //전체 계좌 관리
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		System.out.println("==================================");
		System.out.println("           전체 계좌 관리 ");
		System.out.println("==================================");
		List<AccountInfoVO> voList = managerService.getAllAccountInfo();
		if(voList.size() == 0) {
			System.out.println("     조회할 계좌가 존재하지 않습니다.");
			System.out.println("==================================");
		}else {
			for(AccountInfoVO vo : voList) {
				System.out.print("고객명 : " + vo.getMember_index());
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		
	}
}
