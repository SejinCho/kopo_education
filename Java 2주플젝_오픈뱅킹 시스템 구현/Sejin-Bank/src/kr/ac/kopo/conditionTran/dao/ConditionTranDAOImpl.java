package kr.ac.kopo.conditionTran.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.conditionTran.vo.ConditionTranVO;
import kr.ac.kopo.util.ConnectionFactory;

public class ConditionTranDAOImpl implements ConditionTranDAO {
	
	//예약 이체 정보 insert
	@Override
	public boolean insertReserveTran(ConditionTranVO conditionTran) {
		boolean result = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into sj_condition_transfer(id, type, condition_tran_date ,condition_tran_time , send_account_number,receive_account_number, ");
		sql.append("send_bank_code, receive_bank_code, tran_amt ,my_content,receive_content,tran_state , name, member_id)  ");
		sql.append("values('condition'||sj_seq_condition_transfer.nextval, 'R', ");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, 'R', ?, ?) ");
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, conditionTran.getCondition_tran_date());
			pstmt.setString(2, conditionTran.getCondition_tran_time());
			pstmt.setString(3, conditionTran.getSend_account_number());
			pstmt.setString(4, conditionTran.getReceive_account_number());
			pstmt.setString(5, conditionTran.getSend_bank_code());
			pstmt.setString(6, conditionTran.getReceive_bank_code());
			pstmt.setInt(7, conditionTran.getTran_amt());
			pstmt.setString(8, conditionTran.getMy_content());
			pstmt.setString(9, conditionTran.getReceive_content());
			pstmt.setString(10, conditionTran.getName());
			pstmt.setString(11, conditionTran.getMember_id());
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	//예약이체정보 가져오기(이체 대기중인것)
	@Override
	public List<ConditionTranVO> getReserveTranList(String condition_tran_date, String condition_tran_time) {
		System.out.println("예약이체정보 가져오기 dao 가기 전: " + condition_tran_date + " " + condition_tran_time);
		List<ConditionTranVO> reserveTranList = new ArrayList<ConditionTranVO>();
		StringBuilder sql = new StringBuilder();
		sql.append("select type,send_account_number,receive_account_number,send_bank_code,receive_bank_code,tran_amt,my_content,receive_content,id,member_id     ");
		sql.append("from sj_condition_transfer where condition_tran_date = ? ");
		sql.append("and condition_tran_time = ? and type = 'R' and tran_state='R' ");

		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, condition_tran_date);
			pstmt.setString(2, condition_tran_time);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ConditionTranVO conditionTran = new ConditionTranVO();
				conditionTran.setType(rs.getString("type"));
				conditionTran.setSend_account_number(rs.getString("send_account_number"));
				conditionTran.setReceive_account_number(rs.getString("receive_account_number"));
				conditionTran.setSend_bank_code(rs.getString("send_bank_code"));
				conditionTran.setReceive_bank_code(rs.getString("receive_bank_code"));
				conditionTran.setTran_amt(rs.getInt("tran_amt"));
				conditionTran.setMy_content(rs.getString("my_content"));
				conditionTran.setReceive_content(rs.getString("receive_content"));
				conditionTran.setId(rs.getString("id"));
				conditionTran.setMember_id(rs.getString("member_id"));
				System.out.println("dao 데이터 쌓기 : " + conditionTran.toString());
				reserveTranList.add(conditionTran);
			}
			System.out.println("예약이체정보 가져오기 dao 다녀온 후 : " + reserveTranList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return reserveTranList;
		
	}
	
	//예약이체 보내고 상태 성공으로 변경하기
	@Override
	public boolean updateReserveState(String id) {
		System.out.println("상태 성공으로 변경하기");
		System.out.println("id 값 : " + id);
		boolean result = false;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update sj_condition_transfer set tran_state = 'S' where id = ?  ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
}
