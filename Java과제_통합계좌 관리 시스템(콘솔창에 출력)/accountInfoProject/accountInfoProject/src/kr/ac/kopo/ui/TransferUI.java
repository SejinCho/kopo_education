package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class TransferUI extends BaseUI{	//계좌이체
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		//송금할 은행 정보 확인(여기있는 은행만)
		//송금받을 은행이 여기 테이블에 있는지 확인
		//여기있다면 잔액 update, 없다면 송금한 은행만 update
		
		//map에 저장해서 dao에 보내기
		Map<String, Object> map = new HashMap<String, Object>();
		AccountInfoVO sendVo = null;
		AccountInfoVO recieveVo = null;
		
		
		String send_bank = "";
		String send_account_number = "";
		
		
		//=======================================================
		//	송금할 계좌 정보
		//=======================================================
		
		boolean send_accountInfoBool = false;
		do {
			
			//전체 계좌 조회
			IAccountInfoUI ui = new AccountAllSelectUI();
			ui.contents();
			System.out.println();
			
			send_bank = getString("송금할 은행을 입력하세요.(하나, 농협, 기업, 산업, 국민) : ");
			send_account_number = getString("송금할 계좌를 입력하세요.('-'를 입력하지 마세요.) : ");
			
			//계좌 정보 가져오기
			sendVo = accountInfoService.oneAccountInfo(send_bank, send_account_number, "me");
			
			if(sendVo == null) {
				System.out.println("계좌번호를 잘못 입력했습니다.");
				continue;
			}else {
				String send_account_passwd = getString("계좌의 비밀번호를 입력하세요 : ");
				if(! send_account_passwd.equals(sendVo.getAccount_passwd())) {
					System.out.println("계좌 비밀번호가 일치하지 않습니다.");
					continue;
				}
				
				System.out.println("===========================================");
				System.out.println(send_bank +"은행의 " + send_account_number + " 계좌 정보");
				System.out.println("===========================================");
				System.out.print("별칭 : " + sendVo.getNickname());
				System.out.print(", 계좌번호 : " + sendVo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(sendVo.getBalance()));
				System.out.print(", 등록 날짜 : " + sendVo.getRegistration_date());
				System.out.println();
				
				
				send_accountInfoBool = true;
			}
		}while(! send_accountInfoBool);
		
		map.put("send_bank", send_bank);
		map.put("sendVo", sendVo);
		
		//=======================================================
		//	송금받을 계좌 정보
		//=======================================================
		
		String recieve_bank = getString("송금받을 은행을 선택하세요 : ");
		String recieve_account_number = getString("송금받을 계좌를 입력하세요('-'를 입력하지 마세요.) : ");
		
		//송금받을 계좌 정보 가져오기
		recieveVo = accountInfoService.oneAccountInfo(recieve_bank, recieve_account_number,"other");
		
		
		//얼마 송금할건지
		int money = 0;
		boolean moneyBool = false;
		do {
			money = getInt("얼마를 송금하시겠습니까? : ");
			if(money > sendVo.getBalance()) {
				System.out.println("잔액보다 많이 송금할 수 없습니다. 현재 잔액은 " + format.format(sendVo.getBalance()) + "원 입니다.");
				continue;
			}
			moneyBool = true;
		}while(! moneyBool);
		map.put("money", money);
		
		if(recieveVo == null) { //송금받는 계좌 정보가 여기 테이블에 없는 경우
			map.put("recieve_bank", "null");
			map.put("outside_account_bank", recieve_bank);
			map.put("outside_account_number", recieve_account_number);
			
		}else { //송금받는 계좌 정보가 여기 테이블에 있는 경우
			map.put("recieve_bank", recieve_bank);
			map.put("recieveVo", recieveVo);
		}
		
		boolean result = transferInfoService.transfer(map);
		if(result) {
			System.out.println("계좌이체 완료");
		}else {
			System.out.println("계좌이체 실패");
		}
		
		
		
	}

}
