package kr.ac.kopo.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.dao.ManagerDAO;
import kr.ac.kopo.vo.AccountInfoVO;
import kr.ac.kopo.vo.ManagerVO;
import kr.ac.kopo.vo.MemberVO;

public class ManagerService {
private ManagerDAO managerDao;
	
	public ManagerService() {
		managerDao = new ManagerDAO();
	}
	
	
	//로그인
	public ManagerVO managerLogin(String manager_id) {
		ManagerVO vo = managerDao.managerLogin(manager_id);
		return vo;
	}
	
	//전체 회원정보
	public List<MemberVO> getMemberInfo(String id) {
		List<MemberVO> vo = null;
		if(id.equals("all")) {
			vo = managerDao.getAllMemberInfo();
		}else {
			vo = managerDao.getMemberInfo(id);
		}
		return vo;
	}
	
	//여자 고객 수, 남자 고객 수 
	public Map<Integer, Integer> getStatisticsGender() {
		Map<Integer, Integer> map = managerDao.getStatisticsGender();
		return map;
	}
	
	//전체 계좌 조회
	public List<AccountInfoVO> getAllAccountInfo(){
		List<AccountInfoVO> list = managerDao.getAllAccountInfo();
		return list;
	}
	
}
