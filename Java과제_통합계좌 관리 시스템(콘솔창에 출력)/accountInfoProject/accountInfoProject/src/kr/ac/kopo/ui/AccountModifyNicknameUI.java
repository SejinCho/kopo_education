package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class AccountModifyNicknameUI extends BaseUI{ //별칭 수정
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		//전체 계좌 조회
		IAccountInfoUI ui = new AccountAllSelectUI();
		ui.contents();
		System.out.println();
		
		String bank = getString("수정할 은행을 입력하세요. (하나/기업/국민/산업/농협) : ");
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
		
		String account_number = getString("별칭을 바꿀 계좌번호를 입력하세요 : ");
		String nickname = getString("수정할 별칭을 입력하세요 : ");
		boolean modifyBool = accountInfoService.accountModifyNickname(bank, account_number, nickname);
		
		if(modifyBool) {
			System.out.println("수정 완료");
		}else {
			System.out.println("수정 실패(계좌번호를 제대로 입력해주세요.)");
		}
	}
}
