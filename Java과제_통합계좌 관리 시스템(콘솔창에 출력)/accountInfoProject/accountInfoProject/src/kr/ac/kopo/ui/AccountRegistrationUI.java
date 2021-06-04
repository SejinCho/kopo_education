package kr.ac.kopo.ui;

import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.AccountInfoVO;

public class AccountRegistrationUI extends BaseUI { //계좌등록
	@Override
	public void contents() throws Exception {
		
		String bank = getString("은행을 선택하세요.(하나, 국민, 기업, 농협, 산업) : ");
		
		AccountInfoVO info = new AccountInfoVO();
		Map<String, AccountInfoVO> map = new HashMap<>();
		boolean account_number_boool = false;
		String account_number = "";
		
		if(bank.equals("하나")) {
			info.setAccount_type(1); //1. 등록 2. 계좌 생성
		}
		
		do {
			account_number = getString("계좌번호를 입력하세요(-는 입력 제외) : ");
			
			account_number_boool = accountInfoService.isValid_account_number(account_number);
			if(! account_number_boool) {
				System.out.println("계좌 번호는 '-'를 제외하고 입력합니다.");
			}
		}while(! account_number_boool);
		
		boolean account_passwd_bool = false;
		String account_passwd = "";
		
		do {
			account_passwd = getString("계좌 비밀번호를 입력하세요(4자리 숫자) : ");
			account_passwd_bool = accountInfoService.isValid_account_passwd(account_passwd);
			if(! account_passwd_bool) {
				System.out.println("비밀번호는 4자리 숫자로 입력합니다.");
			}
		}while(! account_passwd_bool);
		
		int money = (int) ((Math.random() * 10 + 1));
		money *= 1000;
		
		int balance = money;
		
		String nickname = getString("계좌 별칭을 정해주세요 : ");
		String member_index = SessionFactory.getSessionInstance("member_index");
		
		info.setAccount_number(account_number);
		info.setAccount_passwd(account_passwd);
		info.setBalance(balance);
		info.setNickname(nickname);
		info.setMember_index(member_index);
		
		map.put(bank, info);
		
		boolean result = accountInfoService.account_registration(map);
		
		if(result) {
			System.out.println("계좌가 등록되었습니다.");
		}else {
			System.out.println("계좌 등록 실패");
		}
		
		
	}

}
