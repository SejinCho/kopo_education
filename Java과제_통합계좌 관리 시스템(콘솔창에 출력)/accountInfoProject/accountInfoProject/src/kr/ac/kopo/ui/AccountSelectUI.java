package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class AccountSelectUI extends BaseUI{ //계좌번호 검색해서 계좌정보 가져오기
	
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		String account_number = "";
		boolean account_number_bool = false;
		do {
			account_number = getString("계좌번호를 입력해주세요('-'는 입력하지 마세요!) : ");
			account_number_bool = accountInfoService.isValid_account_number(account_number);
		}while(! account_number_bool);
		
		Map<String, AccountInfoVO> map = accountInfoService.getAccountselect(account_number,"me");
		
		if(map == null) {
			System.out.println("===========================================");
			System.out.println("   *********계좌가 존재하지 않습니다.*********   ");
			System.out.println("===========================================");
			System.out.println();
		}else {
			Iterator<String> keys = map.keySet().iterator();
			String key = "";
			while(keys.hasNext()) {
				key = keys.next();
			}
			System.out.println("===========================================");
			System.out.println(key +"은행의 " + account_number + " 계좌 정보");
			System.out.println("===========================================");
			System.out.print("별칭 : " + map.get(key).getNickname());
			System.out.print(", 계좌번호 : " + map.get(key).getAccount_number());
			System.out.print(", 잔액 : " + format.format(map.get(key).getBalance()));
			System.out.print(", 등록 날짜 : " + map.get(key).getRegistration_date());
			System.out.println();
		}
		
	}

}
