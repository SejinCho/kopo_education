package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class WithdrawUI extends BaseUI{ //출금 
	
	@Override
	public void contents() throws Exception { 
		DecimalFormat format = new DecimalFormat("###,###");
		boolean accountInfoBool = false;
		String bank = "";
		Map<String, Object> map = new HashMap<String, Object>();
		AccountInfoVO vo = null;
		
		do {
			
			//전체 계좌 조회
			IAccountInfoUI ui = new AccountAllSelectUI();
			ui.contents();
			System.out.println();
			
			bank = getString("출금할 은행을 입력하세요.(하나, 농협, 기업, 산업, 국민) : ");
			String account_number = getString("출금할 계좌를 입력하세요.('-'를 입력하지 마세요.) : ");
			map.put("bank", bank);
			
			//계좌 정보 가져오기
			vo = accountInfoService.oneAccountInfo(bank, account_number,"me");
			
			if(vo == null) {
				System.out.println("계좌번호를 잘못 입력했습니다.");
				continue;
			}else {
				String account_passwd = getString("계좌의 비밀번호를 입력하세요 : ");
				if(! account_passwd.equals(vo.getAccount_passwd())) {
					System.out.println("계좌 비밀번호가 일치하지 않습니다.");
					continue;
				}
				
				System.out.println("===========================================");
				System.out.println(bank +"은행의 " + account_number + " 계좌 정보");
				System.out.println("===========================================");
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
				map.put("account_index", vo.getAccount_index());
				
				accountInfoBool = true;
			}
		}while(! accountInfoBool);
		
		int money = 0;
		boolean moneyBool = false;
		do {
			money = getInt("얼마를 출금하시겠습니까? : ");
			if(money > vo.getBalance()) {
				System.out.println("잔액보다 많이 출금할 수 없습니다. 현재 잔액은 " + format.format(vo.getBalance()) + "원 입니다.");
				continue;
			}
			moneyBool = true;
			map.put("money", -money);
		}while(! moneyBool);
		
		
		boolean depositResult = transferInfoService.balanceUpdate(map);
		if(depositResult) {
			System.out.println("출금 성공");
		}else {
			System.out.println("출금 실패");
		}
		
	}
}
