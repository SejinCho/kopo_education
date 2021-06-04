package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.AccountInfoVO;

public class AccountInfoDAO {
	
	// °èÁÂ µî·Ï(±âÁ¸ °èÁÂ & »õ·Î °èÁÂ »ý¼º)
	public boolean account_registration(Map<String, AccountInfoVO> map) {
		boolean result = false;
		
		//1. ÇÏ³ª, 2. ±¹¹Î, 3. ±â¾÷, 4. ³óÇù, 5. »ê¾÷
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			
			Iterator<String> keys = map.keySet().iterator();
			String key = "";
			while(keys.hasNext()) {
				key = keys.next();
			}
			
			switch (key) {
			case "ÇÏ³ª": //ÇÏ³ª
				sql.append("insert into ai_account_info_hana(account_index, member_index, account_number, account_passwd, balance, nickname) ");
				sql.append("values('hana' || ai_seq_account_info_hana.nextval, ?, ?, ?, ?, ?)");
				break;
			case "±¹¹Î": //±¹¹Î
				sql.append("insert into ai_account_info_kb(account_index, member_index, account_number, account_passwd, balance, nickname) ");
				sql.append("values('kb' || ai_seq_account_info_kb.nextval, ?, ?, ?, ?, ?)");
				break;
			case "±â¾÷": //±â¾÷
				sql.append("insert into ai_account_info_ibk(account_index, member_index, account_number, account_passwd, balance, nickname) ");
				sql.append("values('ibk' || ai_seq_account_info_ibk.nextval, ?, ?, ?, ?, ?)");
				break;
			case "³óÇù": //³óÇù
				sql.append("insert into ai_account_info_nh(account_index, member_index, account_number, account_passwd, balance, nickname) ");
				sql.append("values('nh' || ai_seq_account_info_nh.nextval, ?, ?, ?, ?, ?)");
				break;
			case "»ê¾÷": //»ê¾÷
				sql.append("insert into ai_account_info_kdb(account_index, member_index, account_number, account_passwd, balance, nickname) ");
				sql.append("values('kdb' || ai_seq_account_info_kdb.nextval, ?, ?, ?, ?, ?)");
				break; 
			}
						
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, map.get(key).getMember_index());
			pstmt.setString(2, map.get(key).getAccount_number());
			pstmt.setString(3, map.get(key).getAccount_passwd());
			pstmt.setInt(4, map.get(key).getBalance());
			pstmt.setString(5, map.get(key).getNickname());
			
			int num = pstmt.executeUpdate();
			
			if(num == 1) {
				result = true;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return result ;
	}
	
	//ÀüÃ¼ °èÁÂ Á¶È¸(ÇÏ³ª ÀºÇà) type : all or °èÁÂ¹øÈ£ // who : me, other
	public List<AccountInfoVO> getAccountSelectAll_hana(String account_number, String who) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<AccountInfoVO> list = new ArrayList<AccountInfoVO>();
		AccountInfoVO vo = null;
		
		String member_index = SessionFactory.getSessionInstance("member_index");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select account_index, account_number, account_passwd, balance, nickname, to_char(registration_date,'yyyy-mm-dd') as registration_date from ai_account_info_hana ");
			
			if(! account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ? and account_number = ?");
			}else if(! account_number.equals("all") && who.equals("other")) {
				sql.append("where account_number = ?");
			}else if(account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ?");
			}
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			
			if(! account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
				pstmt.setString(2, account_number);
			}else if(! account_number.equals("all") && who.equals("other")) {
				pstmt.setString(1, account_number);
			}else if(account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				vo = new AccountInfoVO();
				vo.setMember_index(member_index);
				vo.setAccount_passwd(rs.getString("account_passwd"));
				vo.setAccount_index(rs.getString("account_index"));
				vo.setAccount_number(rs.getString("account_number"));
				vo.setBalance(rs.getInt("balance"));
				vo.setNickname(rs.getString("nickname"));
				vo.setRegistration_date(rs.getString("registration_date"));
				list.add(vo);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return list;
	}
	
	//ÀüÃ¼ °èÁÂ Á¶È¸ (³óÇùÀºÇà)
	public List<AccountInfoVO> getAccountSelectAll_nh(String account_number, String who) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<AccountInfoVO> list = new ArrayList<AccountInfoVO>();
		AccountInfoVO vo = null;
		
		String member_index = SessionFactory.getSessionInstance("member_index");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select account_index, account_number, account_passwd,balance, nickname, to_char(registration_date,'yyyy-mm-dd') as registration_date from ai_account_info_nh ");
			
			if(! account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ? and account_number = ?");
			}else if(! account_number.equals("all") && who.equals("other")) {
				sql.append("where account_number = ?");
			}else if(account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ?");
			}
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			
			if(! account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
				pstmt.setString(2, account_number);
			}else if(! account_number.equals("all") && who.equals("other")) {
				pstmt.setString(1, account_number);
			}else if(account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new AccountInfoVO();
				vo.setMember_index(member_index);
				vo.setAccount_passwd(rs.getString("account_passwd"));
				vo.setAccount_index(rs.getString("account_index"));
				vo.setAccount_number(rs.getString("account_number"));
				vo.setBalance(rs.getInt("balance"));
				vo.setNickname(rs.getString("nickname"));
				vo.setRegistration_date(rs.getString("registration_date"));
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return list;
	}
	
	
	//ÀüÃ¼ °èÁÂ Á¶È¸ (±â¾÷ ÀºÇà)
	public List<AccountInfoVO> getAccountSelectAll_ibk(String account_number, String who) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<AccountInfoVO> list = new ArrayList<AccountInfoVO>();
		AccountInfoVO vo = null;
		
		String member_index = SessionFactory.getSessionInstance("member_index");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select account_index, account_number,account_passwd, balance, nickname, to_char(registration_date,'yyyy-mm-dd') as registration_date from ai_account_info_ibk ");
			
			if(! account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ? and account_number = ?");
			}else if(! account_number.equals("all") && who.equals("other")) {
				sql.append("where account_number = ?");
			}else if(account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ?");
			}
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			
			if(! account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
				pstmt.setString(2, account_number);
			}else if(! account_number.equals("all") && who.equals("other")) {
				pstmt.setString(1, account_number);
			}else if(account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new AccountInfoVO();
				vo.setMember_index(member_index);
				vo.setAccount_passwd(rs.getString("account_passwd"));
				vo.setAccount_index(rs.getString("account_index"));
				vo.setAccount_number(rs.getString("account_number"));
				vo.setBalance(rs.getInt("balance"));
				vo.setNickname(rs.getString("nickname"));
				vo.setRegistration_date(rs.getString("registration_date"));
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return list;
	}
	
	//ÀüÃ¼ °èÁÂ Á¶È¸ (»ê¾÷ÀºÇà)
	public List<AccountInfoVO> getAccountSelectAll_kdb(String account_number, String who) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<AccountInfoVO> list = new ArrayList<AccountInfoVO>();
		AccountInfoVO vo = null;
		
		String member_index = SessionFactory.getSessionInstance("member_index");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select account_index, account_number,account_passwd, balance, nickname, to_char(registration_date,'yyyy-mm-dd') as registration_date from ai_account_info_kdb ");
			
			if(! account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ? and account_number = ?");
			}else if(! account_number.equals("all") && who.equals("other")) {
				sql.append("where account_number = ?");
			}else if(account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ?");
			}
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			
			if(! account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
				pstmt.setString(2, account_number);
			}else if(! account_number.equals("all") && who.equals("other")) {
				pstmt.setString(1, account_number);
			}else if(account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new AccountInfoVO();
				vo.setMember_index(member_index);
				vo.setAccount_passwd(rs.getString("account_passwd"));
				vo.setAccount_index(rs.getString("account_index"));
				vo.setAccount_number(rs.getString("account_number"));
				vo.setBalance(rs.getInt("balance"));
				vo.setNickname(rs.getString("nickname"));
				vo.setRegistration_date(rs.getString("registration_date"));
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return list;
	}
	
	
	//ÀüÃ¼ °èÁÂ Á¶È¸ (±¹¹Î)
	public List<AccountInfoVO> getAccountSelectAll_kb(String account_number, String who) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		List<AccountInfoVO> list = new ArrayList<AccountInfoVO>();
		AccountInfoVO vo = null;
		
		String member_index = SessionFactory.getSessionInstance("member_index");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select account_index,account_passwd, account_number, balance, nickname, to_char(registration_date,'yyyy-mm-dd') as registration_date from ai_account_info_kb ");
			
			if(! account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ? and account_number = ?");
			}else if(! account_number.equals("all") && who.equals("other")) {
				sql.append("where account_number = ?");
			}else if(account_number.equals("all") && who.equals("me")) {
				sql.append("where member_index = ?");
			}
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			
			if(! account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
				pstmt.setString(2, account_number);
			}else if(! account_number.equals("all") && who.equals("other")) {
				pstmt.setString(1, account_number);
			}else if(account_number.equals("all") && who.equals("me")) {
				pstmt.setString(1, member_index);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new AccountInfoVO();
				vo.setMember_index(member_index);
				vo.setAccount_passwd(rs.getString("account_passwd"));
				vo.setAccount_index(rs.getString("account_index"));
				vo.setAccount_number(rs.getString("account_number"));
				vo.setBalance(rs.getInt("balance"));
				vo.setNickname(rs.getString("nickname"));
				vo.setRegistration_date(rs.getString("registration_date"));
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return list;
	}
	
	
	//º°Äª ¼öÁ¤
	public boolean accountModifyNickname(String bank, String account_number, String nickname) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			StringBuilder sql = new StringBuilder();
			
			switch (bank) {
			case "ÇÏ³ª":
				sql.append("update ai_account_info_hana set nickname = ? where account_number = ?");
				break;
			case "±â¾÷":
				sql.append("update ai_account_info_ibk set nickname = ? where account_number = ?");
				break;
			
			case "±¹¹Î":
				sql.append("update ai_account_info_kb set nickname = ? where account_number = ?");
				break;
				
			case "»ê¾÷":
				sql.append("update ai_account_info_kdb set nickname = ? where account_number = ?");
				break;
				
			case "³óÇù":
				sql.append("update ai_account_info_nh set nickname = ? where account_number = ?");
				break;
			}
			
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, nickname);
			pstmt.setString(2, account_number);
			
			int num = pstmt.executeUpdate();

			if(num == 1) {
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return result;
	}
	
	//°èÁÂ »èÁ¦
	public boolean accountDelete(String bank, String account_number) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			StringBuilder sql = new StringBuilder();
			
			switch (bank) {
			case "ÇÏ³ª":
				sql.append("delete from ai_account_info_hana where account_number = ?");
				break;
			case "±â¾÷":
				sql.append("delete from ai_account_info_ibk where account_number = ?");
				break;
			
			case "±¹¹Î":
				sql.append("delete from ai_account_info_kb where account_number = ?");
				break;
				
			case "»ê¾÷":
				sql.append("delete from ai_account_info_kdb where account_number = ?");
				break;
				
			case "³óÇù":
				sql.append("delete from ai_account_info_nh where account_number = ?");
				break;
			}
			
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, account_number);
			
			int num = pstmt.executeUpdate();

			if(num == 1) {
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return result;
	}
	
	//°èÁÂ »ý¼º
	public boolean account_create(Map<String, Object> map) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			
			String key = (String) map.get("send_bank");
			int money = ((AccountInfoVO) map.get(key)).getBalance();
			
			//*****¼Û±Ý¾× Â÷°¨******
			switch (key) {
			case "ÇÏ³ª": //ÇÏ³ª
				sql.append("update ai_account_info_hana set balance = (balance + ?) where account_index = ?");
				break;
			case "±¹¹Î": //±¹¹Î
				sql.append("update ai_account_info_kb set balance = (balance + ?) where account_index = ?");
				break;
			case "±â¾÷": //±â¾÷
				sql.append("update ai_account_info_ibk set balance = (balance + ?) where account_index = ?");
				break;
			case "³óÇù": //³óÇù
				sql.append("update ai_account_info_nh set balance = (balance + ?) where account_index = ?");
				break;
			case "»ê¾÷": //»ê¾÷
				sql.append("update ai_account_info_kdb set balance = (balance + ?) where account_index = ?");
				break; 
			}
			
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false); //Æ®·£Àè¼Ç Ã³¸®¸¦ À§ÇØ¼­ auto commitÀ» Áß´Ü
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, money);
			pstmt.setString(2, ((AccountInfoVO) map.get(key)).getAccount_index());
			int num = pstmt.executeUpdate();
			
			
			//****°èÁÂ °³¼³ insert***** 
			sql = new StringBuilder();
			sql.append("insert into ai_account_info_hana(account_index, member_index, account_number, account_passwd, balance, nickname, account_type) ");
			sql.append("values('hana' || ai_seq_account_info_hana.nextval, ?, ?, ?, ?, ?,?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, ((AccountInfoVO) map.get("create")).getMember_index());
			pstmt.setString(2, ((AccountInfoVO) map.get("create")).getAccount_number());
			pstmt.setString(3, ((AccountInfoVO) map.get("create")).getAccount_passwd());
			pstmt.setInt(4, ((AccountInfoVO) map.get("create")).getBalance());
			pstmt.setString(5, ((AccountInfoVO) map.get("create")).getNickname());
			pstmt.setInt(6, ((AccountInfoVO) map.get("create")).getAccount_type());
			
			num += pstmt.executeUpdate();
			
			if(num == 2) {
				conn.commit();
				result = true;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCClose.close(conn, pstmt);
		}

		return result ;
	}
	
	//°èÁÂ 30ÀÏ Áö³µ´ÂÁö È®ÀÎ
	public boolean account_create_check(String member_index) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("select * from ai_account_info_hana where member_index = ? and account_type = 2 and sysdate-REGISTRATION_DATE < 30");
			
			conn = new ConnectionFactory().getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member_index);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = false;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return result;
	}
	

} 
