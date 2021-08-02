package kr.ac.kopo.account.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.dao.AccountDAOImpl;
import kr.ac.kopo.account.vo.AccountInfoVO;
import kr.ac.kopo.account.vo.AccountTransferInfoVO;

public class AccountServiceImpl implements AccountService {
	AccountDAOImpl dao = new AccountDAOImpl();
	
	//계좌 중복 확인
	@Override
	public boolean checkAvailAccountNum(String account_number) {
		return dao.checkAvailAccountNum(account_number);
	}
	
	//계좌 개설
	@Override
	public boolean createAccount(AccountInfoVO accountInfo) {
		return dao.insertAccountInfo(accountInfo);
	}
	
	//한명 계좌 가져오기
	@Override
	public List<AccountInfoVO> getAccountInfoList(String member_id) {
		return dao.getAccountInfoList(member_id);
	}
	
	//계좌정보 하나 가져오기
	@Override
	public AccountInfoVO getAccountInfo(String account_number) {
		return dao.getAccountInfo(account_number);
	}
	
	//세진은행 계좌에서 이체
	@Override
	public boolean insertTransferInfo(AccountTransferInfoVO transferInfo) {
		return dao.insertTransferInfo(transferInfo);
	}
	
	//계좌 비밀번호 가져오기
	@Override
	public String getAccountPassword(String account_number) {
		return dao.getAccountPassword(account_number);
	}
	
	//계좌 거래내역 가져오기
	@Override
	public List<AccountTransferInfoVO> getAccountTransferInfo(Map<String, String> map) {
		return dao.getAccountTransferInfo(map);
	}
	
	//세진은행 -> 다른은행 계좌이체
	@Override
	public boolean transferSend(AccountTransferInfoVO transferInfo) {
		return dao.transferSend(transferInfo);
	}
}
