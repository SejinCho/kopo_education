package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.AccountInfoVO;
import kr.ac.kopo.vo.ManagerVO;
import kr.ac.kopo.vo.MemberVO;

public class ManagerDAO {
	
	//아이디로 매니저 조회
	public ManagerVO managerLogin(String manager_id) {
		ManagerVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from ai_manager where manager_id = ? ");
						
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, manager_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new ManagerVO();
				vo.setManager_id(manager_id);
				vo.setManager_index(rs.getString("manager_index"));
				vo.setManager_passwd(rs.getString("manager_passwd"));
				vo.setJoin_date(rs.getString("join_date"));
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return vo;
	}
	
	//전체 member 정보 가져오기
	public List<MemberVO> getAllMemberInfo() {
		List<MemberVO> voList = new ArrayList<MemberVO>();
		MemberVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select addr,gender,id, to_char(join_date,'yyyy-mm-dd') as join_date");
			sql.append(", name, phone from ai_member");
						
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setAddr(rs.getString("addr"));
				vo.setGender(rs.getInt("gender"));
				vo.setId(rs.getString("id"));
				vo.setJoin_date(rs.getString("join_date"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				voList.add(vo);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return voList;
	}
	
	
	//member 정보 가져오기(id 검색으로)
	public List<MemberVO> getMemberInfo(String id) {
		List<MemberVO> voList = new ArrayList<MemberVO>();
		MemberVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select addr,gender,id, to_char(join_date,'yyyy-mm-dd') as join_date");
			sql.append(", name, phone from ai_member where id like '%' || ? || '%'");
						
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setAddr(rs.getString("addr"));
				vo.setGender(rs.getInt("gender"));
				vo.setId(rs.getString("id"));
				vo.setJoin_date(rs.getString("join_date"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				voList.add(vo);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return voList;
	}
	
	//여자 고객 수, 남자 고객 수 
	public Map<Integer, Integer> getStatisticsGender() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select gender, count(*) as cnt from ai_member group by gender");
						
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getInt("gender"), rs.getInt("cnt"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return map;
	}
	
	//전체 계좌 조회
	public List<AccountInfoVO> getAllAccountInfo() {
		List<AccountInfoVO> voList = new ArrayList<AccountInfoVO>();
		AccountInfoVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select m.name, account.account_number, account.balance, account.nickname,  ");
			sql.append("to_char(account.REGISTRATION_DATE,'yyyy-mm-dd') as REGISTRATION_DATE from");
			sql.append("(select * from ai_account_info_kb ");
			sql.append("union ");
			sql.append("select * from ai_account_info_kdb ");
			sql.append("union ");
			sql.append("select * from ai_account_info_nh ");
			sql.append("union ");
			sql.append("select * from ai_account_info_ibk ");
			sql.append("union ");
			sql.append("select account_index, member_index, account_number, account_passwd, balance, nickname, REGISTRATION_DATE from ai_account_info_hana) account, ");
			sql.append("ai_member m where account.member_index = m.member_index");
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new AccountInfoVO();
				vo.setMember_index(rs.getString("name"));
				vo.setAccount_number(rs.getString("account_number"));
				vo.setBalance(rs.getInt("balance"));
				vo.setNickname(rs.getString("nickname"));
				vo.setRegistration_date(rs.getString("REGISTRATION_DATE"));
				voList.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return voList;
	}
}
