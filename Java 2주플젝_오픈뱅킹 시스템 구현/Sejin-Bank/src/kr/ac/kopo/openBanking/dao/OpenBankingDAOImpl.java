package kr.ac.kopo.openBanking.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.openBanking.vo.OpenBankingVO;
import kr.ac.kopo.util.ConnectionFactory;

public class OpenBankingDAOImpl implements OpenBankingDAO {
	
	
	//오픈뱅킹 동의 확인
	@Override
	public boolean checkTermsOfService(String member_id) {
		boolean result = false;
		String agreeCheck = "";
		
		StringBuilder sql = new StringBuilder();
		sql.append("select PRIVACY_AGREE_YN from sj_member where id = ?  ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				agreeCheck = rs.getString("PRIVACY_AGREE_YN");
			}
			
			if(agreeCheck.equals("Y")) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	//오픈뱅킹 동의
	@Override
	public boolean serviceAgree(String member_id) {
		boolean result = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update sj_member set PRIVACY_AGREE_YN = 'Y', PRIVACY_AGREE_DATE = sysdate where id = ?  ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member_id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	
	//종범쓰 테이블에서 해당 유저의 계좌정보 가져오기
	@Override
	public List<OpenBankingVO> accountInfoList_J(String jumin_no) {
		List<OpenBankingVO> accountInfoList = new ArrayList<OpenBankingVO>();
		OpenBankingVO accountInfo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.ACCT_NO as account_number, a.ACCT_PWD account_password, a.ACCT_NAME as nickname, ");
		sql.append("a.BALANCE as balance, m.name as name, m.SSN as jumin_no, to_char(a.REG_DATE,'yyyy-mm-dd') as reg_date ");
		sql.append("from BANK_ACCOUNT@JBMorgan_link a , BANK_MEMBER@JBMorgan_link m ");
		sql.append("where a.member_id = m.member_id ");
		sql.append("and m.ssn = ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, jumin_no);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				accountInfo = new OpenBankingVO();
				accountInfo.setAccount_number(rs.getString("account_number"));
				accountInfo.setAccount_password(rs.getString("account_password"));
				accountInfo.setNickname(rs.getString("nickname") );
				accountInfo.setBalance(rs.getInt("balance"));
				accountInfo.setReg_date(rs.getString("reg_date"));
				accountInfo.setBank_code("J");
				accountInfo.setBank_name("JBMorgan");
				accountInfoList.add(accountInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return accountInfoList;
	}
	
	//소영쓰 테이블에서 해당 유저의 계좌정보 가져오기
	@Override
	public List<OpenBankingVO> accountInfoList_D(String jumin_no) {
		List<OpenBankingVO> accountInfoList = new ArrayList<OpenBankingVO>();
		OpenBankingVO accountInfo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.ACCOUNT as account_number, a.ACCPWD account_password, a.ACCALIAS as nickname, ");
		sql.append("a.BALANCE as balance, m.name as name, m.RES_CODE as jumin_no, to_char(a.ENROLL_DT,'yyyy-mm-dd') as reg_date  ");
		sql.append("from ACCOUNTDB@DONJO_link a , USERINFO@DONJO_link m ");
		sql.append("where a.id = m.ID  ");
		sql.append("and m.RES_CODE =  ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, jumin_no);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				accountInfo = new OpenBankingVO();
				accountInfo.setAccount_number(rs.getString("account_number"));
				accountInfo.setAccount_password(rs.getString("account_password"));
				accountInfo.setNickname(rs.getString("nickname") );
				accountInfo.setBalance(rs.getInt("balance"));
				accountInfo.setReg_date(rs.getString("reg_date"));
				accountInfo.setBank_code("D");
				accountInfo.setBank_name("DONJO");
				accountInfoList.add(accountInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return accountInfoList;
	}
	
	//현석쓰 테이블에서 해당 유저의 계좌정보 가져오기
	@Override
	public List<OpenBankingVO> accountInfoList_Y(String jumin_no) {
		List<OpenBankingVO> accountInfoList = new ArrayList<OpenBankingVO>();
		OpenBankingVO accountInfo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.ACCOUNT_NUMBER as account_number, a.customer_account_pwd account_password, a.CUSTOMER_ACCOUNT_ALIAS as nickname, ");
		sql.append("a.CUSTOMER_ACCOUNT_CHANGE as balance, m.CUSTOMER_NAME as name, m.CUSTOMER_JUMIN_NO as jumin_no,  ");
		sql.append("to_char(a.CUSTOMER_ACCOUNT_INPUT_DATE ,'yyyy-mm-dd') as reg_date ");
		sql.append("from CUSTOMER_ACCOUNT@YGBank_link a , CUSTOMER_TB@YGBank_link m ");
		sql.append("where a.CUSTOMER_SQ = m.CUSTOMER_SQ ");
		sql.append("and m.CUSTOMER_JUMIN_NO = ?");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, jumin_no);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				accountInfo = new OpenBankingVO();
				accountInfo.setAccount_number(rs.getString("account_number"));
				accountInfo.setAccount_password(rs.getString("account_password"));
				accountInfo.setNickname(rs.getString("nickname") );
				accountInfo.setBalance(rs.getInt("balance"));
				accountInfo.setReg_date(rs.getString("reg_date"));
				accountInfo.setBank_code("Y");
				accountInfo.setBank_name("YGBank");
				accountInfoList.add(accountInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return accountInfoList;
	}
	
	//소영쓰 은행에서 특정 계좌 정보 가져오기
	@Override
	public OpenBankingVO accountInfo_D(String account_number) {
		OpenBankingVO accountInfo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.ACCOUNT as account_number, a.ACCPWD account_password, a.ACCALIAS as nickname, ");
		sql.append("a.BALANCE as balance, to_char(a.ENROLL_DT,'yyyy-mm-dd') as reg_date ");
		sql.append("from ACCOUNTDB@DONJO_link a ");
		sql.append("where a.ACCOUNT = ? " );
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				accountInfo = new OpenBankingVO();
				accountInfo.setAccount_number(rs.getString("account_number"));
				accountInfo.setAccount_password(rs.getString("account_password"));
				accountInfo.setNickname(rs.getString("nickname") );
				accountInfo.setBalance(rs.getInt("balance"));
				accountInfo.setReg_date(rs.getString("reg_date"));
				accountInfo.setBank_code("D");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return accountInfo;
	}
	
	//종범쓰 은행에서 특정 계좌 정보 가져오기
	@Override
	public OpenBankingVO accountInfo_J(String account_number) {
		OpenBankingVO accountInfo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.ACCT_NO as account_number, a.ACCT_PWD account_password, a.ACCT_NAME as nickname,  ");
		sql.append("a.BALANCE as balance, to_char(a.REG_DATE,'yyyy-mm-dd') as reg_date   ");
		sql.append("from BANK_ACCOUNT@JBMorgan_link a  ");
		sql.append("where a.ACCT_NO = ? " );
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				accountInfo = new OpenBankingVO();
				accountInfo.setAccount_number(rs.getString("account_number"));
				accountInfo.setAccount_password(rs.getString("account_password"));
				accountInfo.setNickname(rs.getString("nickname") );
				accountInfo.setBalance(rs.getInt("balance"));
				accountInfo.setReg_date(rs.getString("reg_date"));
				accountInfo.setBank_code("J");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return accountInfo;
	}
	
	//현석쓰 은행에서 특정 계좌정보 가져오기
	@Override
	public OpenBankingVO accountInfo_Y(String account_number) {
		OpenBankingVO accountInfo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.ACCOUNT_NUMBER as account_number, a.customer_account_pwd account_password, a.CUSTOMER_ACCOUNT_ALIAS as nickname, ");
		sql.append("a.CUSTOMER_ACCOUNT_CHANGE as balance, to_char(a.CUSTOMER_ACCOUNT_INPUT_DATE ,'yyyy-mm-dd') as reg_date  ");
		sql.append("from CUSTOMER_ACCOUNT@YGBank_link a where a.account_number = ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				accountInfo = new OpenBankingVO();
				accountInfo.setAccount_number(rs.getString("account_number"));
				accountInfo.setAccount_password(rs.getString("account_password"));
				accountInfo.setNickname(rs.getString("nickname") );
				accountInfo.setBalance(rs.getInt("balance"));
				accountInfo.setReg_date(rs.getString("reg_date"));
				accountInfo.setBank_code("Y");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return accountInfo;
	}
	
	//다른은행 -> 다른은행 계좌이체
	@Override
	public boolean transferReceive(AccountTransferInfoVO transferInfo) {
		boolean result = false;
		String sql = "";
		
		sql = "{? = call transfer_open(?,?,?,?,?,?,?,?) }";
		
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			CallableStatement cstmt = conn.prepareCall(sql);
		) {
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, transferInfo.getName());
			cstmt.setString(3, transferInfo.getSend_account_number());
			cstmt.setString(4, transferInfo.getSend_bank_code());
			cstmt.setString(5, transferInfo.getReceive_account_number());
			cstmt.setString(6, transferInfo.getReceive_bank_code());
			cstmt.setInt(7, transferInfo.getTran_amt());
			cstmt.setString(8, transferInfo.getMy_content());
			cstmt.setString(9, transferInfo.getReceive_content());
			
			cstmt.execute(); 
			int cnt = cstmt.getInt(1);
			if(cnt == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	//오픈뱅킹 거래내역 가져오기 (조건 여러개)
	//소영은행 -- 입금A/출금M
	@Override
	public List<AccountTransferInfoVO> transferInfoList_D(Map<String, String> map) {
		List<AccountTransferInfoVO> transferInfoList = new ArrayList<AccountTransferInfoVO>();
		AccountTransferInfoVO transferInfo = null;
		
		String account_number = map.get("account_number");
		String start_date = map.get("start_date");
		String end_date = map.get("end_date");
		String inout_type = map.get("inout_type");
		String order = map.get("order");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select to_char(TRAN_DT,'yyyy-mm-dd') as tran_date , to_char(TRAN_DT,'hh24:mi:ss') as tran_time,  ");
		sql.append("BALANCE as tran_amt, TR_CODE as inout_type from TRANHISTORY@DONJO_link ");
		sql.append("where MYACC = ? ");
		sql.append("and to_char(TRAN_DT,'yyyy-mm-dd') between ? and ? ");
		
		if(inout_type.equals("in")) { //in, out
			sql.append("and TR_CODE = 'A' ");
			
		}else if(inout_type.equals("out")) {
			sql.append("and TR_CODE = 'M' ");
		} 
		
		if(order.equals("desc")) { //최신순
			sql.append("order by TRAN_DT desc  ");
			
		}else {
			sql.append("order by TRAN_DT ");
		}
		
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				transferInfo = new AccountTransferInfoVO();
				transferInfo.setTran_amt(rs.getInt("tran_amt"));
				transferInfo.setInout_type(rs.getString("inout_type"));
				transferInfo.setTran_date(rs.getString("tran_date"));
				transferInfo.setTran_time(rs.getString("tran_time"));
				transferInfoList.add(transferInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return transferInfoList;
	}
	//종범은행
	@Override
	public List<AccountTransferInfoVO> transferInfoList_J(Map<String, String> map) {
		List<AccountTransferInfoVO> transferInfoList = new ArrayList<AccountTransferInfoVO>();
		AccountTransferInfoVO transferInfo = null;
		
		String account_number = map.get("account_number");
		String start_date = map.get("start_date");
		String end_date = map.get("end_date");
		String inout_type = map.get("inout_type");
		String order = map.get("order");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select to_char(TRANSACTION_DATE,'yyyy-mm-dd') as tran_date , to_char(TRANSACTION_DATE,'hh24:mi:ss') as tran_time, ");
		sql.append("type as inout_type, amount as tran_amt from BANK_TRANSACTION@JBMorgan_link where ACCT_NO = ?  ");
		sql.append("and to_char(TRANSACTION_DATE,'yyyy-mm-dd') between ? and ? ");
		
		if(inout_type.equals("in")) { //in, out
			sql.append("and type = '입금' ");
			
		}else if(inout_type.equals("out")) {
			sql.append("and type = '출금' ");
		} 
		
		if(order.equals("desc")) { //최신순
			sql.append("order by TRANSACTION_DATE desc ");
			
		}else {
			sql.append("order by TRANSACTION_DATE ");
		}
		
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				transferInfo = new AccountTransferInfoVO();
				transferInfo.setTran_amt(rs.getInt("tran_amt"));
				transferInfo.setInout_type(rs.getString("inout_type"));
				transferInfo.setTran_date(rs.getString("tran_date"));
				transferInfo.setTran_time(rs.getString("tran_time"));
				
				transferInfoList.add(transferInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return transferInfoList;
	}
	//현석은행
	@Override
	public List<AccountTransferInfoVO> transferInfoList_Y(Map<String, String> map) {
		List<AccountTransferInfoVO> transferInfoList = new ArrayList<AccountTransferInfoVO>();
		AccountTransferInfoVO transferInfo = null;
		
		String account_number = map.get("account_number");
		String start_date = map.get("start_date");
		String end_date = map.get("end_date");
		String inout_type = map.get("inout_type");
		String order = map.get("order");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select to_char(h.HISTORY_DATE,'yyyy-mm-dd') as tran_date , to_char(h.HISTORY_DATE,'hh24:mi:ss') as tran_time,    ");
		sql.append("h.MODIFY_CHANGE as tran_amt, h.HISTORY_TASK as inout_type from HISTORY@YGBank_link h, CUSTOMER_ACCOUNT@YGBank_link a ");
		sql.append("where h.CUSTOMER_ACCOUNT_SQ = a.CUSTOMER_ACCOUNT_SQ ");
		sql.append("and a.account_number = ? ");
		sql.append("and to_char(HISTORY_DATE,'yyyy-mm-dd') between ? and ? ");
		
		if(inout_type.equals("in")) { //in, out
			sql.append("and h.HISTORY_TASK = '입금' ");
			
		}else if(inout_type.equals("out")) {
			sql.append("and h.HISTORY_TASK = '출금' ");
		} 
		
		if(order.equals("desc")) { //최신순
			sql.append("order by HISTORY_DATE desc  ");
			
		}else {
			sql.append("order by HISTORY_DATE  ");
		}
		
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				transferInfo = new AccountTransferInfoVO();
				transferInfo.setTran_amt(rs.getInt("tran_amt"));
				transferInfo.setInout_type(rs.getString("inout_type"));
				transferInfo.setTran_date(rs.getString("tran_date"));
				transferInfo.setTran_time(rs.getString("tran_time"));
				
				transferInfoList.add(transferInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return transferInfoList;
	}
	
	
}
