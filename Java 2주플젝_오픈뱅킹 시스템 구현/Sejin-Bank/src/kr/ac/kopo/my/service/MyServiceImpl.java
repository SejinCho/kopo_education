package kr.ac.kopo.my.service;

import java.util.List;

import kr.ac.kopo.conditionTran.vo.ConditionTranVO;
import kr.ac.kopo.my.dao.MyDAOImpl;

public class MyServiceImpl implements MyService {
	MyDAOImpl dao = new MyDAOImpl();
	//여태 예약이체 한 거 list
	@Override
	public List<ConditionTranVO> myConditionTranList(String member_id) {
		return dao.myConditionTranList(member_id);
	}
}
