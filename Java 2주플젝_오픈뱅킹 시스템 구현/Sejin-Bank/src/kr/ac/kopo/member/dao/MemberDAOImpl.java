package kr.ac.kopo.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.ConnectionFactory;

public class MemberDAOImpl implements MemberDAO {
	
	//id, pw 로그인
	@Override
	public MemberVO loginCheck(String user_id, String user_password) {
		MemberVO member = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name, easy_password, email, kakao_id, phone, gender, birth, ");
		sql.append("zipcode, addr1_load, addr1_jibun , addr2, receive_sms_yn, privacy_agree_yn, ");
		sql.append("to_char(privacy_agree_date,'yyyy-mm-dd') as privacy_agree_date, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date ");
		sql.append("from sj_member where user_id = ? and user_password = ? and withdrawal_date is null");
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_password);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberVO();
				String id = rs.getString("id");
				String name = rs.getString("name");
				String easy_password = rs.getString("easy_password");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String gender = rs.getString("gender");
				String birth = rs.getString("birth");
				String zipcode = rs.getString("zipcode");
				String addr1_load = rs.getString("addr1_load");
				String addr1_jibun = rs.getString("addr1_jibun");
				String addr2 = rs.getString("addr2");
				String receive_sms_yn = rs.getString("receive_sms_yn");
				String privacy_agree_yn = rs.getString("addr2");
				String privacy_agree_date = rs.getString("privacy_agree_date");
				String reg_date = rs.getString("reg_date");
				
				member.setId(id);
				member.setName(name);
				member.setEasy_password(easy_password);
				member.setEmail(email);
				member.setPhone(phone);
				member.setGender(gender);
				member.setBirth(birth);
				member.setZipcode(zipcode);
				member.setAddr1_load(addr1_load);
				member.setAddr1_jibun(addr1_jibun);
				member.setAddr2(addr2);
				member.setReceive_sms_yn(receive_sms_yn);
				member.setPrivacy_agree_yn(privacy_agree_yn);
				member.setPrivacy_agree_date(privacy_agree_date);
				member.setReg_date(reg_date);
				member.setKakao_id(rs.getString("kakao_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	//카카오 로그인
	@Override
	public MemberVO selectOne(String kakao_id) {
		MemberVO member = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name, easy_password, email, kakao_id, phone, gender, birth, ");
		sql.append("zipcode, addr1_load, addr1_jibun , addr2, receive_sms_yn, privacy_agree_yn, ");
		sql.append("to_char(privacy_agree_date,'yyyy-mm-dd') as privacy_agree_date, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date ");
		sql.append("from sj_member where kakao_id = ? and withdrawal_date is null");
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, kakao_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberVO();
				String id = rs.getString("id");
				String name = rs.getString("name");
				String easy_password = rs.getString("easy_password");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String gender = rs.getString("gender");
				String birth = rs.getString("birth");
				String zipcode = rs.getString("zipcode");
				String addr1_load = rs.getString("addr1_load");
				String addr1_jibun = rs.getString("addr1_jibun");
				String addr2 = rs.getString("addr2");
				String receive_sms_yn = rs.getString("receive_sms_yn");
				String privacy_agree_yn = rs.getString("addr2");
				String privacy_agree_date = rs.getString("privacy_agree_date");
				String reg_date = rs.getString("reg_date");
				
				member.setId(id);
				member.setName(name);
				member.setEasy_password(easy_password);
				member.setEmail(email);
				member.setPhone(phone);
				member.setGender(gender);
				member.setBirth(birth);
				member.setZipcode(zipcode);
				member.setAddr1_load(addr1_load);
				member.setAddr1_jibun(addr1_jibun);
				member.setAddr2(addr2);
				member.setReceive_sms_yn(receive_sms_yn);
				member.setPrivacy_agree_yn(privacy_agree_yn);
				member.setPrivacy_agree_date(privacy_agree_date);
				member.setReg_date(reg_date);
				member.setKakao_id(kakao_id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	@Override
	public boolean insertOne(MemberVO member) { //유저 정보 insert(회원가입)
		boolean result = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into sj_member(id, name, easy_password, email, ");
		sql.append("kakao_id, phone, gender, birth, zipcode, addr1_load, addr1_jibun, ");
		sql.append("addr2, receive_sms_yn, jumin_no, user_id, user_password) values('member' || sj_seq_member_id.nextval, ");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,?)");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEasy_password());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getKakao_id());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getGender());
			pstmt.setString(7, member.getBirth());
			pstmt.setString(8, member.getZipcode());
			pstmt.setString(9, member.getAddr1_load());
			pstmt.setString(10, member.getAddr1_jibun());
			pstmt.setString(11, member.getAddr2());
			pstmt.setString(12, member.getReceive_sms_yn());
			pstmt.setString(13, member.getJumin_no());
			pstmt.setString(14, member.getUser_id());
			pstmt.setString(15, member.getUser_password());
			
			int num = pstmt.executeUpdate();
			
			if(num == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//유저 아이디 중복체크
	@Override
	public boolean vaildUserId(String user_id) {
		boolean result = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) as cnt from sj_member where user_id = ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				if(cnt == 0) {
					result = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	//주민번호 가져오기
	@Override
	public String getJuminNo(String id) {
		String jumin_no = "";
		
		StringBuilder sql = new StringBuilder();
		sql.append("select jumin_no from sj_member where id = ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			jumin_no = rs.getString("jumin_no");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jumin_no;
	}
}
