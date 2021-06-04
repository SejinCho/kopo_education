package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.AccountInfoVO;

//계좌 생성
public class AccountCreateUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		System.out.println("============================================");
		System.out.println("             하나은행 계좌 생성               ");
		System.out.println("============================================");
		
		boolean createCheckBool = accountInfoService.account_create_check(SessionFactory.getSessionInstance("member_index"));
		if(! createCheckBool) {
			System.out.println("30일 전에 계좌를 생성했습니다. 계좌를 생성할 수 없습니다.T_T");
			return;
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		AccountInfoVO createVo = new AccountInfoVO();
		
		createVo.setAccount_type(2); //새로운 계좌 생성
		createVo.setMember_index(SessionFactory.getSessionInstance("member_index")); //멤버 인덱스
		
		boolean account_passwd_bool = false;
		String account_passwd = "";
		do {
			account_passwd = getString("생성할 계좌의 비밀번호를 입력하세요.(4자리) : ");
			account_passwd_bool = accountInfoService.isValid_account_passwd(account_passwd);
			if(! account_passwd_bool) {
				System.out.println("비밀번호는 4자리로 설정해야 합니다.");
			}
			String repasswd = getString("비밀번호를 다시 입력해주세요 : ");
			if(!account_passwd.equals(repasswd)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				account_passwd_bool = false;
			}
			
		}while(!account_passwd_bool);
		
		createVo.setAccount_passwd(account_passwd); //비밀번호
		
		String nickname = getString("생성할 계좌의 별칭을 설정하세요. : ");
		createVo.setNickname(nickname); //계좌 별칭
		
		Random r = new Random();
		String account_number = "";
		
		//14자리 숫자
		for(int i = 0; i<14 ; ++i) {
			int t = r.nextInt(10) ;
			account_number += t;
		}
		
		createVo.setAccount_number(account_number); //계좌번호
		
		
		
		System.out.println("****계좌 최초 생성 시 1000원 이상 입금되어야 합니다.****");
		
		
		String send_bank = "";
		String send_account_number = "";
		AccountInfoVO sendVo = new AccountInfoVO();
		
		AccountInfoVO vo = null;
		
		boolean accountInfoBool = false;
		do {
			//전체 계좌 조회
			IAccountInfoUI ui = new AccountAllSelectUI();
			ui.contents();
			System.out.println();
			send_bank = getString("송금할 은행을 입력하세요.(하나, 농협, 기업, 산업, 국민) : ");
			
			boolean send_account_number_bool = false;
			do {
				send_account_number = getString("송금할 계좌를 입력하세요.('-'를 입력하지 마세요.) : ");
				send_account_number_bool = accountInfoService.isValid_account_number(send_account_number);
				if(! send_account_number_bool) {
					System.out.println("'-'를 입력하지 마세요.");
				}
			}while(! send_account_number_bool);
			
			
			//계좌 정보 가져오기
			vo = accountInfoService.oneAccountInfo(send_bank, send_account_number,"me");
			
			if(vo == null) {
				System.out.println("계좌번호를 잘못 입력했습니다.");
				continue;
				
			}else {
				String send_account_passwd = getString("계좌의 비밀번호를 입력하세요 : ");
				if(! send_account_passwd.equals(vo.getAccount_passwd())) {
					System.out.println("계좌 비밀번호가 일치하지 않습니다.");
					continue;
				}
				
				System.out.println("===========================================");
				System.out.println(send_bank +"은행의 " + send_account_number + " 계좌 정보");
				System.out.println("===========================================");
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
				
				
				accountInfoBool = true;
			}
		}while(! accountInfoBool);
		
		//돈 송금
		int money = 0;
		boolean moneyBool = false;
		do {
			money = getInt("얼마를 송금하시겠습니까? : ");
			if(money > vo.getBalance()) {
				System.out.println("잔액보다 많이 송금할 수 없습니다. 현재 잔액은 " + format.format(vo.getBalance()) + "원 입니다.");
				continue;
			}else if(money <1000){
				System.out.println("1000원보다 많이 송금하셔야 합니다.");
				continue;
			}
			moneyBool = true;
		}while(! moneyBool);
		
		sendVo.setAccount_index(vo.getAccount_index());
		sendVo.setBalance(-money);
		createVo.setBalance(money);
		
		map.put(send_bank, sendVo);
		map.put("create", createVo);
		map.put("send_bank", send_bank);
		
		boolean depositResult = accountInfoService.account_create(map);
		if(depositResult) {
			System.out.println("계좌 개설 성공");
		}else {
			System.out.println("계좌 개설 실패");
		}
		
		
	}	
}
