package kr.ac.kopo.openBanking.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.openBanking.vo.OpenBankingVO;

public interface OpenBankingService {
	
	//오픈뱅킹 동의 확인
	public boolean checkTermsOfService(String member_id);
	//오픈뱅킹 동의
	public boolean serviceAgree(String member_id);
	
	public Map<String, List<OpenBankingVO>> accountInfoList(String jumin_no); //오픈뱅킹 계좌 리스트 가져오기(주민)
	public OpenBankingVO accountInfo(String account_number, String bank_code) ; //특정 계좌정보 가져오기
	public List<OpenBankingVO> accountInfoList_code(String jumin_no, String bank_code); //오픈뱅킹 계좌 리스트 가져오기(은행코드로)
	public boolean transferReceive(AccountTransferInfoVO transferInfo); //계좌이체
	
	//오픈뱅킹 거래내역 가져오기 (조건 여러개)
	public List<AccountTransferInfoVO> transferInfoList(Map<String, String> map) ;
	
}
