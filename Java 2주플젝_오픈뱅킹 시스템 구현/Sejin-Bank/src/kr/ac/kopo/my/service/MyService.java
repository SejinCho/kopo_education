package kr.ac.kopo.my.service;

import java.util.List;

import kr.ac.kopo.conditionTran.vo.ConditionTranVO;

public interface MyService {
	public List<ConditionTranVO> myConditionTranList(String member_id); //여태 예약이체 한 거 list
}
