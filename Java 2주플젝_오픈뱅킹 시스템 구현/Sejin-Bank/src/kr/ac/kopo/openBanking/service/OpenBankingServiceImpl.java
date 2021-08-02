package kr.ac.kopo.openBanking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.openBanking.dao.OpenBankingDAOImpl;
import kr.ac.kopo.openBanking.vo.OpenBankingVO;

public class OpenBankingServiceImpl implements OpenBankingService {
	OpenBankingDAOImpl dao = new OpenBankingDAOImpl();
	
	//오픈뱅킹 동의 확인
	@Override
	public boolean checkTermsOfService(String member_id) {
		return dao.checkTermsOfService(member_id);
	}
	
	//오픈뱅킹 동의
	@Override
	public boolean serviceAgree(String member_id) {
		return dao.serviceAgree(member_id);
	}
	
	//오픈뱅킹 계좌 리스트 가져오기(전체)
	@Override
	public Map<String, List<OpenBankingVO>> accountInfoList(String jumin_no) {
		Map<String, List<OpenBankingVO>> map = new HashMap<String,List<OpenBankingVO>>();
		
		//종범은행
		map.put("J", dao.accountInfoList_J(jumin_no) ) ;
		
		//현석 (테스트 해야함!!!!!!!!!)
		map.put("Y", dao.accountInfoList_Y(jumin_no)) ;
		
		//소영
		map.put("D", dao.accountInfoList_D(jumin_no));
		
		return map;
	}
	
	//오픈뱅킹 특정 계좌 정보 가져오기
	@Override
	public OpenBankingVO accountInfo(String account_number, String bank_code) {
		OpenBankingVO accountInfo = null;
		System.out.println("특정 계좌정보 가져오기 dao : " + bank_code);
		if(bank_code.equals("J")) { //종범은행
			accountInfo = dao.accountInfo_J(account_number);
			
		}else if(bank_code.equals("Y")) { //현석 은행
			accountInfo = dao.accountInfo_Y(account_number);
			
		}else if(bank_code.equals("D")) { //소영은행
			accountInfo = dao.accountInfo_D(account_number);
		
		}else if(bank_code.equals("all")) {
			accountInfo = dao.accountInfo_J(account_number);
			if(accountInfo == null ) {
				accountInfo = dao.accountInfo_Y(account_number);
				if(accountInfo == null) {
					accountInfo = dao.accountInfo_D(account_number);
				}
			}
		}
		
		return accountInfo;
	}
	
	//은행 코드로 계좌 리스트 가져오기
	@Override
	public List<OpenBankingVO> accountInfoList_code(String jumin_no, String bank_code) {
		// TODO Auto-generated method stub
		List<OpenBankingVO> accountInfoList = null;
		
		if(bank_code.equals("J")) { //종범은행
			accountInfoList = dao.accountInfoList_J(jumin_no);
			
		}else if(bank_code.equals("Y")) { //현석 은행 
			accountInfoList = dao.accountInfoList_Y(jumin_no);
			
		}else if(bank_code.equals("D")) { //소영은행
			accountInfoList = dao.accountInfoList_D(jumin_no);
		}
		
		return accountInfoList;
	}
	
	//계좌이체
	@Override
	public boolean transferReceive(AccountTransferInfoVO transferInfo) {
		return dao.transferReceive(transferInfo);
	}
	
	//오픈뱅킹 거래내역 가져오기 (조건 여러개)
	@Override
	public List<AccountTransferInfoVO> transferInfoList(Map<String, String> map) {
		String bank_code = map.get("bank_code");
		List<AccountTransferInfoVO> transferInfoList = null;
		
		if(bank_code.equals("J")) { //종범은행
			transferInfoList = dao.transferInfoList_J(map);
			
		}else if(bank_code.equals("Y")) { //현석 은행 
			transferInfoList = dao.transferInfoList_Y(map);
			
		}else if(bank_code.equals("D")) { //소영은행
			transferInfoList = dao.transferInfoList_D(map);
		}
		
		return transferInfoList;
	}
}
