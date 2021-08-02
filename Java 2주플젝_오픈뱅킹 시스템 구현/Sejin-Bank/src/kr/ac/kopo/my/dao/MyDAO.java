package kr.ac.kopo.my.dao;

import java.util.List;

import kr.ac.kopo.conditionTran.vo.ConditionTranVO;

public interface MyDAO {
	public List<ConditionTranVO> myConditionTranList(String member_id); //여태 예약이체 한 거 list
}
