package kr.ac.kopo.account.dao;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.vo.AccountInfoVO;
import kr.ac.kopo.account.vo.AccountTransferInfoVO;

public interface AccountDAO {
	public List<AccountInfoVO> getAccountInfoList(String member_id) ; //유저 한명 계좌 리스트
	public String getEasyPassword(String member_id); //간편 비밀번호 가져오기
	public boolean insertAccountInfo(AccountInfoVO accountInfo); //계좌 개설
	public boolean checkAvailAccountNum(String account_number) ; //계좌 중복 확인
	public AccountInfoVO getAccountInfo(String account_number); //계좌정보 하나 가져오기
	public List<AccountTransferInfoVO> getAccountTransferInfo(Map<String, String> map); //계좌 거래내역 가져오기
	public boolean insertTransferInfo(AccountTransferInfoVO transferInfo); //세진은행 계좌에서 이체한 내역 insert
	public String getAccountPassword(String account_number); //계좌 비번 가져오기
	
	//세진은행 -> 다른은행 계좌이체
	public boolean transferSend(AccountTransferInfoVO transferInfo);
}
