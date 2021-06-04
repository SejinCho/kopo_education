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
	
	
	//�α���
	public ManagerVO managerLogin(String manager_id) {
		ManagerVO vo = managerDao.managerLogin(manager_id);
		return vo;
	}
	
	//��ü ȸ������
	public List<MemberVO> getMemberInfo(String id) {
		List<MemberVO> vo = null;
		if(id.equals("all")) {
			vo = managerDao.getAllMemberInfo();
		}else {
			vo = managerDao.getMemberInfo(id);
		}
		return vo;
	}
	
	//���� �� ��, ���� �� �� 
	public Map<Integer, Integer> getStatisticsGender() {
		Map<Integer, Integer> map = managerDao.getStatisticsGender();
		return map;
	}
	
	//��ü ���� ��ȸ
	public List<AccountInfoVO> getAllAccountInfo(){
		List<AccountInfoVO> list = managerDao.getAllAccountInfo();
		return list;
	}
	
}
