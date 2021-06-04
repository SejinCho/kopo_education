package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class AccountAllSelectUI extends BaseUI{//전체 계좌조회
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		System.out.println("========================================");
		System.out.println("            전체 계좌 조회");
		
		
		Map<String, List<AccountInfoVO>> map = accountInfoService.accountSelect("all","me");
		List<AccountInfoVO> voList = new ArrayList<AccountInfoVO>();
		
		if(map.size() == 0) {
			System.out.println("========================================");
			System.out.println("         등록된 계좌가 존재하지 않습니다.");
			System.out.println("========================================");
		}
		
		//하나은행
		voList = map.get("하나");
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            하나 은행");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		//기업은행
		voList = map.get("기업");
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            기업 은행");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		//국민은행
		voList = map.get("국민");
		
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            국민 은행");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		//산업은행
		voList = map.get("산업");
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            산업 은행");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
		//농협은행
		voList = map.get("농협");
		if(voList.size() != 0) {
			System.out.println("========================================");
			System.out.println("            농협 은행");
			System.out.println("========================================");
			for(AccountInfoVO vo : voList) {
				System.out.print("별칭 : " + vo.getNickname());
				System.out.print(", 계좌번호 : " + vo.getAccount_number());
				System.out.print(", 잔액 : " + format.format(vo.getBalance()));
				System.out.print(", 등록 날짜 : " + vo.getRegistration_date());
				System.out.println();
			}
		}
		
	}
	
}
