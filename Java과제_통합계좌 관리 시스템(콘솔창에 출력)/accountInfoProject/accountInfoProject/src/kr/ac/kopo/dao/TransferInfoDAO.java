package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.AccountInfoVO;
import kr.ac.kopo.vo.TransferInfoVO;

public class TransferInfoDAO { //������ü, ���, �Ա�
	
	
	//�ܾ� ����(�Ա�,���)
	public boolean balanceUpdate(Map<String, Object> map) { 
		boolean result = false;
		
		//1. �ϳ�, 2. ����, 3. ���, 4. ����, 5. ���
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			String bank = (String) map.get("bank");
			String account_index = (String) map.get("account_index");
			int money = (int) map.get("money");

			switch (bank) {
			case "�ϳ�": //�ϳ�
				sql.append("update ai_account_info_hana set balance = (balance + ?) where account_index = ?");
				break;
			case "����": //����
				sql.append("update ai_account_info_kb set balance = (balance + ?) where account_index = ?");
				break;
			case "���": //���
				sql.append("update ai_account_info_ibk set balance = (balance + ?) where account_index = ?");
				break;
			case "����": //����
				sql.append("update ai_account_info_nh set balance = (balance + ?) where account_index = ?");
				break;
			case "���": //���
				sql.append("update ai_account_info_kdb set balance = (balance + ?) where account_index = ?");
				break; 
			}
						
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, money);
			pstmt.setString(2, account_index);

			int num = pstmt.executeUpdate();
			
			//�ŷ� ���� insert
			sql = new StringBuilder();
			sql.append("insert into ai_transferInfo(transferInfo_index,send_member_index ,receive_member_index, send_account_index,receive_account_index,money) ");
			sql.append("values('transfer'||ai_seq_transferInfo.nextval,?,?,?,?,?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, SessionFactory.getSessionInstance("member_index"));
			pstmt.setString(2, SessionFactory.getSessionInstance("member_index"));
			pstmt.setString(3, account_index);
			pstmt.setString(4, account_index);
			pstmt.setInt(5, money);
			
			num += pstmt.executeUpdate();
			
			if(num == 2) {
				result = true;
			}
			
			conn.commit();
		}catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCClose.close(conn, pstmt);
		}

		return result ;
	}
	
	//������ü
	public boolean transfer(Map<String, Object> map) {
		boolean result = false;
		//1. �ϳ�, 2. ����, 3. ���, 4. ����, 5. ���
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			
			String send_bank = (String) map.get("send_bank");
			AccountInfoVO sendVo = (AccountInfoVO) map.get("sendVo");
			
			String recieve_bank = (String) map.get("recieve_bank"); //���� ���ٸ� null
			
			AccountInfoVO recieveVo = (AccountInfoVO) map.get("recieveVo");
			
			String outside_account_bank = "";
			String outside_account_number = "";
			
			if(recieve_bank.equals("null")) {
				outside_account_bank = (String) map.get("outside_account_bank");
				outside_account_number = (String) map.get("outside_account_number");
			}
			
			int money = (int) map.get("money");
			
			//�۱��ϴ� ���� �ܾ׿� -money 
			switch (send_bank) {
			case "�ϳ�": //�ϳ�
				sql.append("update ai_account_info_hana set balance = (balance - ?) where account_index = ?");
				break;
			case "����": //����
				sql.append("update ai_account_info_kb set balance = (balance - ?) where account_index = ?");
				break;
			case "���": //���
				sql.append("update ai_account_info_ibk set balance = (balance - ?) where account_index = ?");
				break;
			case "����": //����
				sql.append("update ai_account_info_nh set balance = (balance - ?) where account_index = ?");
				break;
			case "���": //���
				sql.append("update ai_account_info_kdb set balance = (balance - ?) where account_index = ?");
				break; 
			}
						
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, money);
			pstmt.setString(2, sendVo.getAccount_index());


			int num = pstmt.executeUpdate();
			
			//�۱��ϴ� ���� �ŷ����� insert
			sql = new StringBuilder();
			if(! map.get("recieve_bank").equals("null")) { //�۱ݹ޴� ���°� ���� ��ϵǾ� �ִ� ���
				sql.append("insert into ai_transferInfo(transferInfo_index,send_member_index,receive_member_index,send_account_index,receive_account_index,money) ");
				sql.append("values('transfer'||ai_seq_transferInfo.nextval,?,?,?,?,?)");
			} else { //�۱ݹ޴� ���°� ���� ��ϵǾ� ���� �ʴ� ���
				sql.append("insert into ai_transferInfo(transferInfo_index,send_member_index,send_account_index,outside_account_number,outside_account_bank,money) ");
				sql.append("values('transfer'||ai_seq_transferInfo.nextval, ?,?,?,?,?)");
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			if(! map.get("recieve_bank").equals("null")) {//�۱ݹ޴� ���°� ���� ��ϵǾ� �ִ� ���
				pstmt.setString(1, SessionFactory.getSessionInstance("member_index"));
				pstmt.setString(2, recieveVo.getMember_index()); //�޴»�� index
				pstmt.setString(3, sendVo.getAccount_index()); //���� index
				pstmt.setString(4, recieveVo.getAccount_index()); //�޴»�� ���� index
				pstmt.setInt(5, money); //��
				
			}else {//�۱ݹ޴� ���°� ���� ��ϵǾ� ���� �ʴ� ���
				pstmt.setString(1, SessionFactory.getSessionInstance("member_index"));
				pstmt.setString(2, sendVo.getAccount_index()); //���� index
				pstmt.setString(3, outside_account_number); //���� index
				pstmt.setString(4, outside_account_bank); //�޴»�� ���� index
				pstmt.setInt(5, money); //��
			}
			
			num += pstmt.executeUpdate();
			
			
			//�۱ݹ޴� ���°� ���� ��ϵǾ� �ִ°�� �ܾ� + money
			sql = new StringBuilder();
			
			if(! map.get("recieve_bank").equals("null")) {
				switch (recieve_bank) {
				case "�ϳ�": //�ϳ�
					sql.append("update ai_account_info_hana set balance = (balance + ?) where account_index = ?");
					break;
				case "����": //����
					sql.append("update ai_account_info_kb set balance = (balance + ?) where account_index = ?");
					break;
				case "���": //���
					sql.append("update ai_account_info_ibk set balance = (balance + ?) where account_index = ?");
					break;
				case "����": //����
					sql.append("update ai_account_info_nh set balance = (balance + ?) where account_index = ?");
					break;
				case "���": //���
					sql.append("update ai_account_info_kdb set balance = (balance + ?) where account_index = ?");
					break; 
				}
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, money);
				pstmt.setString(2, recieveVo.getAccount_index());
				num += pstmt.executeUpdate();
				
				if(num == 3) {
					conn.commit();
					result = true;
				}
				
			}else {
				if(num == 2) {
					conn.commit();
					result = true;
				}
			}
			
		}catch (Exception e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.setAutoCommit(true);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCClose.close(conn, pstmt);
		}
		return result;
	}
	
	//��ü ���� ��������(���� 0�̸� ��ü)
	public List<TransferInfoVO> getTransferInfo(int rownum){
		
		List<TransferInfoVO> voList = new ArrayList<TransferInfoVO>();
		TransferInfoVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select transferInfo_index, send_member_index, receive_member_index, ");
			sql.append("send_account_index, receive_account_index, outside_account_number, ");
			sql.append("outside_account_bank, money, to_char(transfer_date, 'yyyy-mm-dd') as transfer_date ");
			sql.append("from ai_transferInfo where send_member_index = ? or receive_member_index = ? ");
			sql.append("order by transfer_date desc) ");
			
			if(rownum != 0) {
				sql.append("where rownum <= ?");
			}
			
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, SessionFactory.getSessionInstance("member_index"));
			pstmt.setString(2, SessionFactory.getSessionInstance("member_index"));
			if(rownum != 0) {
				pstmt.setInt(3, rownum);
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new TransferInfoVO();
				vo.setTransferInfo_index(rs.getString("transferInfo_index"));
				vo.setSend_member_index(rs.getString("send_member_index"));
				vo.setReceive_member_index(rs.getString("receive_member_index"));
				vo.setSend_account_index(rs.getString("send_account_index"));
				vo.setReceive_account_index(rs.getString("receive_account_index"));
				vo.setOutside_account_number(rs.getString("outside_account_number"));
				vo.setOutside_account_bank(rs.getString("outside_account_bank"));
				vo.setMoney(rs.getInt("money"));
				vo.setTransfer_date(rs.getString("transfer_date"));
				voList.add(vo);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return voList ;
	}
	
}
