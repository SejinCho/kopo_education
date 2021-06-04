package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.MemberVO;

public class MemberDAO {
	
	//아이디 중복 검사
	public boolean isValid_Id(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) as cnt from ai_member where id = ?");
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("cnt") == 0) {
					result = true;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result ;
	}
	
	//핸드폰 번호 중복 검사
	public boolean isValid_Phone(String phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) as cnt from ai_member where phone = ?");
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, phone);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("cnt") == 0) {
					result = true;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return result ;
	}
	
	//회원가입
	public boolean join(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into ai_member(member_index, id, passwd, name, phone, addr, gender) ");
			sql.append("values('member' || ai_seq_member.nextval, ?, ?, ?, ?, ?, ? )");
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassWd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddr());
			pstmt.setInt(6, member.getGender());
			
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
	
	//id나 index로 member 1명 정보 가져오기
	public MemberVO getMemberInfo(Map<String, String> map) { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		MemberVO member = new MemberVO();
		
		try {
			Iterator<String> keys = map.keySet().iterator();
			String key = "";
			while(keys.hasNext()) {
				key = keys.next();
			}
			
			StringBuilder sql = new StringBuilder();
			if(key.equals("id")) {
				sql.append("select * from ai_member where id = ?");
			}else {
				sql.append("select * from ai_member where member_index = ?");
			}

			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, map.get(key));
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				member.setId(rs.getString("id"));
				member.setMember_index(rs.getString("member_index"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setAddr(rs.getString("addr"));
				member.setGender(rs.getInt("gender"));
				member.setJoin_date(rs.getString("join_date"));
				member.setPassWd(rs.getString("passwd"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return  member;
		
	}
}
