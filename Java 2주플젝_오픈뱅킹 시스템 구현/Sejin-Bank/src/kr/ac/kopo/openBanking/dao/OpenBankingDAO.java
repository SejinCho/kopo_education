package kr.ac.kopo.openBanking.dao;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.openBanking.vo.OpenBankingVO;

public interface OpenBankingDAO {
	//오픈뱅킹 동의 확인
	public boolean checkTermsOfService(String member_id);
	//오픈뱅킹 동의
	public boolean serviceAgree(String member_id);
	
	//오픈뱅킹 계좌 리스트
	public List<OpenBankingVO> accountInfoList_J(String jumin_no); //종범은행
	public List<OpenBankingVO> accountInfoList_D(String jumin_no); //소영은행
	public List<OpenBankingVO> accountInfoList_Y(String jumin_no); //현석은행
	
	//오픈뱅킹 특정 계좌 정보 가져오기
	public OpenBankingVO accountInfo_J(String account_number); //종범
	public OpenBankingVO accountInfo_Y(String account_number); //현석
	public OpenBankingVO accountInfo_D(String account_number); //소영
	
	//다른은행 -> 세진은행 계좌이체
	public boolean transferReceive(AccountTransferInfoVO transferInfo);
	
	//오픈뱅킹 거래내역 가져오기 (조건 여러개)
	public List<AccountTransferInfoVO> transferInfoList_J(Map<String, String> map) ; //종범은행
	public List<AccountTransferInfoVO> transferInfoList_D(Map<String, String> map) ; //소영은행
	public List<AccountTransferInfoVO> transferInfoList_Y(Map<String, String> map) ; //현석은행
}
