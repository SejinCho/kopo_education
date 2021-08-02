package kr.ac.kopo.conditionTran.dao;

import java.util.List;

import kr.ac.kopo.conditionTran.vo.ConditionTranVO;

public interface ConditionTranDAO {
	public boolean insertReserveTran(ConditionTranVO conditionTran); //예약 이체 정보 insert
	public List<ConditionTranVO> getReserveTranList(String condition_tran_date , String condition_tran_time);//예약이체정보 가져오기(이체 대기중인것)
	public boolean updateReserveState(String id) ; //예약이체 보내고 상태 성공으로 변경하기
	
}
