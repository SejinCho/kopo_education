package kr.ac.kopo.service;

import java.util.Map;
import java.util.regex.Pattern;

import kr.ac.kopo.dao.MemberDAO;
import kr.ac.kopo.vo.MemberVO;

public class MemberService {
	private MemberDAO memberDao;
	
	public MemberService() {
		memberDao = new MemberDAO();
	}
	
	//ȸ������
	public boolean join(MemberVO member) {
		boolean result = memberDao.join(member);
		return result;
	}
	
	//���̵� �ߺ� �˻�
	public boolean isValid_Id(String id) {
		boolean result = memberDao.isValid_Id(id);
		
		return result;
	}
	
	//�ڵ��� ��ȣ �ߺ� �˻� & ����
	public int isValid_Phone(String phone) {
		int result = 0; // 0 = ������ �ùٸ��� ����, 1 = �ߺ�, 2 = ��ȿ�� ��� 
		
		//1. ���� �˻�
		if(! Pattern.matches("(01[016789])-(\\d{3,4})-(\\d{4})", phone)) {
			return result;
		}
		
		//2. �ߺ� �˻�
		if(memberDao.isValid_Phone(phone)) {
			result = 2;
		}else {
			result = 1;
		}
		
		return result;
	}
	
	//id�� index�� member 1�� ���� ��������
	public MemberVO getMemberInfo(Map<String, String> map) { //type�� id or member_index
		MemberVO member= memberDao.getMemberInfo(map);
		return member;
	}
	
	
	
	
}
