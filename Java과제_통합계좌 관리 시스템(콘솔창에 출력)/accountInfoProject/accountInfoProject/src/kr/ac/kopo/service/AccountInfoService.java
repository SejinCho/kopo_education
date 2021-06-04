package kr.ac.kopo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import kr.ac.kopo.dao.AccountInfoDAO;
import kr.ac.kopo.vo.AccountInfoVO;

public class AccountInfoService {
	private AccountInfoDAO accountInfoDao;
	
	public AccountInfoService() {
		accountInfoDao = new AccountInfoDAO();
	}
	
	//���� ���
	public boolean account_registration(Map<String, AccountInfoVO> map) {
		boolean result = accountInfoDao.account_registration(map);
		//1. �ϳ�, 2. ����, 3. ���, 4. ����, 5. ���
		
		return result ;
	}
	
	//���¹�ȣ ��ȿ�� �˻�
	public boolean isValid_account_number(String account_number) {
		boolean result = false;
		
		if(Pattern.matches("^[0-9]*$", account_number)) {
			result = true;
		}
		
		return result;
	}
	
	//���� ��й�ȣ ��ȿ�� �˻�
	public boolean isValid_account_passwd(String account_passwd) {
		boolean result = false;
		if(Pattern.matches("^[0-9]{4}$", account_passwd)) {
			result = true;
		}
		
		return result;
	}
	
	//���� ��ü ��ȸ (���ະ ���°˻�(������ �˰��ִ� ���) or ��ü �˻�)
	public Map<String, List<AccountInfoVO>> accountSelect(String type, String who)	{
		List<AccountInfoVO> list = new ArrayList<AccountInfoVO>();
		Map<String, List<AccountInfoVO>> map = new HashMap<>();
		
		if(type.equals("all")) { //��ü ���� ��ȸ
			//key�� �����
			list = accountInfoDao.getAccountSelectAll_hana("all", who); //�ϳ�
			map.put("�ϳ�",list);
			
			list = accountInfoDao.getAccountSelectAll_ibk("all", who); // ���
			map.put("���", list);
			
			
			list = accountInfoDao.getAccountSelectAll_kb("all", who); //����
			map.put("����", list);
			
			
			list = accountInfoDao.getAccountSelectAll_kdb("all", who); //���
			map.put("���", list);
			
			
			list = accountInfoDao.getAccountSelectAll_nh("all", who); //����
			map.put("����", list);
			
		}else { //���ະ ��ü ���� ��ȸ
			switch (type) {
			case "�ϳ�":
				list = accountInfoDao.getAccountSelectAll_hana("all", who); //�ϳ�
				map.put("�ϳ�",list);
				break;

			case "���":
				list = accountInfoDao.getAccountSelectAll_ibk("all", who); // ���
				map.put("���", list);
				break;
				
			case "����":
				list = accountInfoDao.getAccountSelectAll_kb("all", who); //����
				map.put("����", list);
				break;
				
			case "���":
				list = accountInfoDao.getAccountSelectAll_kdb("all", who); //���
				map.put("���", list);
				break;
				
			case "����":
				list = accountInfoDao.getAccountSelectAll_nh("all", who); //����
				map.put("����", list);
				break;
			}
		}
		return map;
	}
	
	
	//��Ī ����
	public boolean accountModifyNickname(String bank, String account_number, String nickname) {
		boolean result = accountInfoDao.accountModifyNickname(bank, account_number, nickname);
		return result;
	}
	
	//���� ����
	public boolean accountDelete(String bank, String account_number) {
		boolean result = accountInfoDao.accountDelete(bank, account_number);
		return result;
	}
	
	//���¹�ȣ �˻��ؼ� �������� ��������
	public Map<String, AccountInfoVO> getAccountselect(String account_number, String who) {
		Map<String, AccountInfoVO> map = new HashMap<>();
		List<AccountInfoVO> vo = new ArrayList<AccountInfoVO>();
		
		vo = accountInfoDao.getAccountSelectAll_hana(account_number, who); //�ϳ�
		if(vo.size() == 0) {
			vo = accountInfoDao.getAccountSelectAll_ibk(account_number, who);
		} else {
			map.put("�ϳ�", vo.get(0));
			return map;
		}
		
		if(vo.size() == 0) {
			vo = accountInfoDao.getAccountSelectAll_kb(account_number, who);
		}else {
			map.put("���", vo.get(0));
			return map;
		}
		
		if(vo.size() == 0) {
			vo = accountInfoDao.getAccountSelectAll_kdb(account_number, who);
		}else {
			map.put("����", vo.get(0));
			return map;
		}
		
		if(vo.size() == 0) {
			vo = accountInfoDao.getAccountSelectAll_nh(account_number, who);
		}else {
			map.put("���",vo.get(0) );
			return map;
		}
		if(vo.size() == 0) {
			map = null;
		}else {
			map.put("����", vo.get(0));
		}
		return map;
	}
	
	//���� index�� ���� ���� ��������
	public Map<String, Object> getAccountNumber(String account_index, String who) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		AccountInfoVO getVo = new AccountInfoVO();
		List<AccountInfoVO> voList = null;
		String bank = "";
		//�ϳ�
		voList = accountInfoDao.getAccountSelectAll_hana("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "�ϳ�";
				map.put("bank", bank);
			}
		}
		
		//����
		voList = accountInfoDao.getAccountSelectAll_nh("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "����";
				map.put("bank", bank);
			}
		}
		
		//���
		voList = accountInfoDao.getAccountSelectAll_ibk("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "���";
				map.put("bank", bank);
			}
		}
		
		//���
		voList = accountInfoDao.getAccountSelectAll_kdb("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "���";
				map.put("bank", bank);
			}
		}
		
		//����
		voList = accountInfoDao.getAccountSelectAll_kb("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "����";
				map.put("bank", bank);
			}
		}
		return map;
	}
	
	//Ư�� ���� ���� �������� (����� ���������� �̿��ؼ�)
	public AccountInfoVO oneAccountInfo(String bank, String account_number, String who) {
		AccountInfoVO vo = null;
		
		switch (bank) {
		case "�ϳ�":
			if(accountInfoDao.getAccountSelectAll_hana(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_hana(account_number,who).get(0);
			}
			break;
			
		case "����":
			if(accountInfoDao.getAccountSelectAll_nh(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_nh(account_number, who).get(0);
			}
			break;
			
		case "���":
			if(accountInfoDao.getAccountSelectAll_ibk(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_ibk(account_number, who).get(0);
			}
			break;
			
		case "���":
			if(accountInfoDao.getAccountSelectAll_kdb(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_kdb(account_number, who).get(0);
			}
			break;
			
		case "����":
			if(accountInfoDao.getAccountSelectAll_kb(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_kb(account_number, who).get(0);
			}
			break;

		}
		
		
		return vo;
	}
	
	//���� ����
	public boolean account_create(Map<String, Object> map) {
		boolean result = accountInfoDao.account_create(map);
		return result;
		
	}
	
	//���� �������� 30�� �ȵǾ����� ������
	public boolean account_create_check(String member_index) {
		boolean result = accountInfoDao.account_create_check(member_index);
		return result;
	}
}
