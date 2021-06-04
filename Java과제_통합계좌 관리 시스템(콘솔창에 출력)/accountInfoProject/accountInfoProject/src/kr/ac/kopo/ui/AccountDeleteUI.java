package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class AccountDeleteUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		//전체 계좌 조회
		IAccountInfoUI ui = new AccountAllSelectUI();
		ui.contents();
		System.out.println();
		
		String bank = getString("삭제할 계좌의 은행을 입력해주세요(하나, 국민, 기업, 농협, 산업)  : ");
		Map<String, List<AccountInfoVO>> map = accountInfoService.accountSelect(bank,"me");
		
		List<AccountInfoVO> voList = new ArrayList<>();
		voList = map.get(bank);
		
		if(voList != null) {
			System.out.println("========================================");
			System.out.println("            " + bank  + " 은행");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		String account_number = getString("삭제할 계좌번호를 입력하세요 : ");
		boolean deleteBool = accountInfoService.accountDelete(bank, account_number);
		if(deleteBool) {
			System.out.println("계좌 삭제 성공");
		}else {
			System.out.println("계좌 삭제 실패");
		}
	}
}
