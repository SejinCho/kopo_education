package kr.ac.kopo.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.conditionTran.vo.ConditionTranVO;
import kr.ac.kopo.util.ConnectionFactory;

public class MyDAOImpl implements MyDAO {
	
	//여태 예약이체 한 거 list
	@Override
	public List<ConditionTranVO> myConditionTranList(String member_id) {
		List<ConditionTranVO> reserveTranList = new ArrayList<ConditionTranVO>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from SJ_CONDITION_TRANSFER where member_id = ? order by reg_date desc ");

		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member_id);
			
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
				conditionTran.setCondition_tran_date(rs.getString("condition_tran_date"));
				conditionTran.setCondition_tran_time(rs.getString("condition_tran_time"));
				conditionTran.setTran_state(rs.getString("tran_state"));
				reserveTranList.add(conditionTran);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return reserveTranList;
	}
}
