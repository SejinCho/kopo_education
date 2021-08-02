package kr.ac.kopo.account.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.account.vo.AccountInfoVO;
import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO{
	
	//나의 계좌 리스트 조회
	@Override
	public List<AccountInfoVO> getAccountInfoList(String member_id) {
		List<AccountInfoVO> accountInfoList = new ArrayList<AccountInfoVO>();
		AccountInfoVO accountInfo = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select id, account_number, balance, nickname, account_type, account_status, ");
		sql.append("to_char(reg_date,'yyyy-mm-dd') as r_date from  sj_account_info where member_id = ?");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				accountInfo = new AccountInfoVO();
				accountInfo.setId(rs.getString("id"));
				accountInfo.setAccount_number(rs.getString("account_number"));
				accountInfo.setBalance(rs.getInt("balance"));
				accountInfo.setNickname(rs.getString("nickname"));
				accountInfo.setAccount_type(rs.getInt("account_type"));
				accountInfo.setAccount_status(rs.getInt("account_status"));
				accountInfo.setReg_date(rs.getString("r_date"));
				accountInfoList.add(accountInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return accountInfoList;
	}
	
	//간편 비밀번호 가져오기
	@Override
	public String getEasyPassword(String member_id) {
		String easy_password = "";
		
		StringBuilder sql = new StringBuilder();
		sql.append("select easy_password from sj_member where id = ?");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				easy_password = rs.getString("easy_password");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return easy_password;
	}
	
	//계좌 개설
	@Override
	public boolean insertAccountInfo(AccountInfoVO accountInfo) {
		boolean result = false;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into sj_account_info(id, member_id, account_number, account_password, account_status, nickname ) ");
		sql.append("values('accountInfo' || sj_seq_account_info_id .nextval, ?, ?, ?, ?, ? )");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, accountInfo.getMember_id());
			pstmt.setString(2, accountInfo.getAccount_number());
			pstmt.setString(3, accountInfo.getAccount_password());
			pstmt.setInt(4, accountInfo.getAccount_status());
			pstmt.setString(5, accountInfo.getNickname());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	//계좌 중복 확인
	@Override
	public boolean checkAvailAccountNum(String account_number) {
		boolean result = false;
		int cnt = 1;
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) as cnt from sj_account_info where account_number = ?");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt == 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//계좌정보 하나 가져오기
	@Override
	public AccountInfoVO getAccountInfo(String account_number) {
		
		AccountInfoVO accountInfo = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id, account_number, balance, nickname, account_type, account_status, ");
		sql.append("to_char(reg_date,'yyyy-mm-dd') as r_date from  sj_account_info where account_number = ?");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				accountInfo = new AccountInfoVO();
				accountInfo.setId(rs.getString("id"));
				accountInfo.setAccount_number(rs.getString("account_number"));
				accountInfo.setBalance(rs.getInt("balance"));
				accountInfo.setNickname(rs.getString("nickname"));
				accountInfo.setAccount_type(rs.getInt("account_type"));
				accountInfo.setAccount_status(rs.getInt("account_status"));
				accountInfo.setReg_date(rs.getString("r_date"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountInfo;
	}
	
	//계좌 거래내역 가져오기(조건 붙어서)
	@Override
	public List<AccountTransferInfoVO> getAccountTransferInfo(Map<String, String> map) {
		
		List<AccountTransferInfoVO> transferInfoList = new ArrayList<AccountTransferInfoVO>();
		AccountTransferInfoVO transferInfo = null;
		String account_number = map.get("account_number");
		String start_date = map.get("start_date");
		String end_date = map.get("end_date");
		String inout_type = map.get("inout_type");
		String order = map.get("order");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t.id, t.account_number, t.bank_code , t.tran_amt, t.content,  ");
		sql.append("t.inout_type, t.tran_date, t.tran_time ");
		sql.append(", (select bank_name from sj_bank_code where bank_code = t.bank_code) as bank_name ");
		sql.append("from sj_account_transfer_info t where account_number = ?");
		
		sql.append("and tran_date between ? and ? ");
		
		if(inout_type.equals("in")) { //in, out
			sql.append("and inout_type = 'I' ");
			
		}else if(inout_type.equals("out")) {
			sql.append("and inout_type = 'O' ");
		} 
		
		if(order.equals("desc")) { //최신순
			sql.append("order by tran_date desc , tran_time desc");
			
		}else {
			sql.append("order by tran_date , tran_time ");
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
					transferInfo.setId(rs.getString("id"));
					transferInfo.setAccount_number(rs.getString("account_number"));
					transferInfo.setBank_code(rs.getString("bank_code"));
					transferInfo.setTran_amt(rs.getInt("tran_amt"));
					transferInfo.setContent(rs.getString("content"));
					transferInfo.setInout_type(rs.getString("inout_type"));
					transferInfo.setTran_date(rs.getString("tran_date"));
					transferInfo.setTran_time(rs.getString("tran_time"));
					transferInfo.setBank_name(rs.getString("bank_name"));
					
					transferInfoList.add(transferInfo);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return transferInfoList;
	}
	
	/*세진 은행에서 다른 계좌로 이체했을 때 거래내역 insert*/
	//생성한 프로시저 사용
	@Override
	public boolean insertTransferInfo(AccountTransferInfoVO transferInfo) {
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
	
	
	//계좌 비번 가져오기
	@Override
	public String getAccountPassword(String account_number) {
		String account_password = "";
		
		StringBuilder sql = new StringBuilder();
		sql.append("select account_password from sj_account_info where account_number = ?");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, account_number);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				account_password = rs.getString("account_password");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return account_password;
	}
	
	//계좌이체
	@Override
	public boolean transferSend(AccountTransferInfoVO transferInfo) {
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
}
