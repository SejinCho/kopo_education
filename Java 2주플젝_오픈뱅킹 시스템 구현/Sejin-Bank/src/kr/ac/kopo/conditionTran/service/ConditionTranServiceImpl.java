package kr.ac.kopo.conditionTran.service;

import java.util.List;

import kr.ac.kopo.conditionTran.dao.ConditionTranDAOImpl;
import kr.ac.kopo.conditionTran.vo.ConditionTranVO;

public class ConditionTranServiceImpl implements ConditionTranService {
	
	ConditionTranDAOImpl dao = new ConditionTranDAOImpl();
	
	//예약 이체정보 insert
	@Override
	public boolean insertReserveTran(ConditionTranVO conditionTran) {
		return dao.insertReserveTran(conditionTran);
	}
	
	//예약이체정보 가져오기(이체 대기중인것)
	@Override
	public List<ConditionTranVO> getReserveTranList(String condition_tran_date, String condition_tran_time) {
		return dao.getReserveTranList(condition_tran_date, condition_tran_time);
	}
	
	//예약이체 보내고 상태 성공으로 변경하기
	@Override
	public boolean updateReserveState(String id) {
		return dao.updateReserveState(id);
	}
}
