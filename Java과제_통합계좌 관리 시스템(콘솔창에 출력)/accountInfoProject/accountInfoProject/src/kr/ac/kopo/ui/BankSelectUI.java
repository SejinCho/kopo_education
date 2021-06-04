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
		System.out.println("                은행별 검색  ");
		System.out.println("========================================");
		String bank = getString("검색할 은행을 입력해주세요.(하나, 농협, 기업, 산업, 국민) : ");
		
		Map<String, List<AccountInfoVO>> map = accountInfoService.accountSelect(bank,"me");
		List<AccountInfoVO> voList = map.get(bank);
		
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("           "+ bank +" 은행");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
			}
		}else {
			System.out.println("========================================");
			System.out.println("          "+ bank +"은행의 계좌가 존재하지 않습니다.");
			System.out.println("========================================");
		}
		
	}
}
