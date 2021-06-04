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
	
	//계좌 등록
	public boolean account_registration(Map<String, AccountInfoVO> map) {
		boolean result = accountInfoDao.account_registration(map);
		//1. 하나, 2. 국민, 3. 기업, 4. 농협, 5. 산업
		
		return result ;
	}
	
	//계좌번호 유효성 검사
	public boolean isValid_account_number(String account_number) {
		boolean result = false;
		
		if(Pattern.matches("^[0-9]*$", account_number)) {
			result = true;
		}
		
		return result;
	}
	
	//계좌 비밀번호 유효성 검사
	public boolean isValid_account_passwd(String account_passwd) {
		boolean result = false;
		if(Pattern.matches("^[0-9]{4}$", account_passwd)) {
			result = true;
		}
		
		return result;
	}
	
	//계좌 전체 조회 (은행별 계좌검색(은행을 알고있는 경우) or 전체 검색)
	public Map<String, List<AccountInfoVO>> accountSelect(String type, String who)	{
		List<AccountInfoVO> list = new ArrayList<AccountInfoVO>();
		Map<String, List<AccountInfoVO>> map = new HashMap<>();
		
		if(type.equals("all")) { //전체 계좌 조회
			//key가 은행명
			list = accountInfoDao.getAccountSelectAll_hana("all", who); //하나
			map.put("하나",list);
			
			list = accountInfoDao.getAccountSelectAll_ibk("all", who); // 기업
			map.put("기업", list);
			
			
			list = accountInfoDao.getAccountSelectAll_kb("all", who); //국민
			map.put("국민", list);
			
			
			list = accountInfoDao.getAccountSelectAll_kdb("all", who); //산업
			map.put("산업", list);
			
			
			list = accountInfoDao.getAccountSelectAll_nh("all", who); //농협
			map.put("농협", list);
			
		}else { //은행별 전체 계좌 조회
			switch (type) {
			case "하나":
				list = accountInfoDao.getAccountSelectAll_hana("all", who); //하나
				map.put("하나",list);
				break;

			case "기업":
				list = accountInfoDao.getAccountSelectAll_ibk("all", who); // 기업
				map.put("기업", list);
				break;
				
			case "국민":
				list = accountInfoDao.getAccountSelectAll_kb("all", who); //국민
				map.put("국민", list);
				break;
				
			case "산업":
				list = accountInfoDao.getAccountSelectAll_kdb("all", who); //산업
				map.put("산업", list);
				break;
				
			case "농협":
				list = accountInfoDao.getAccountSelectAll_nh("all", who); //농협
				map.put("농협", list);
				break;
			}
		}
		return map;
	}
	
	
	//별칭 수정
	public boolean accountModifyNickname(String bank, String account_number, String nickname) {
		boolean result = accountInfoDao.accountModifyNickname(bank, account_number, nickname);
		return result;
	}
	
	//계좌 삭제
	public boolean accountDelete(String bank, String account_number) {
		boolean result = accountInfoDao.accountDelete(bank, account_number);
		return result;
	}
	
	//계좌번호 검색해서 계좌정보 가져오기
	public Map<String, AccountInfoVO> getAccountselect(String account_number, String who) {
		Map<String, AccountInfoVO> map = new HashMap<>();
		List<AccountInfoVO> vo = new ArrayList<AccountInfoVO>();
		
		vo = accountInfoDao.getAccountSelectAll_hana(account_number, who); //하나
		if(vo.size() == 0) {
			vo = accountInfoDao.getAccountSelectAll_ibk(account_number, who);
		} else {
			map.put("하나", vo.get(0));
			return map;
		}
		
		if(vo.size() == 0) {
			vo = accountInfoDao.getAccountSelectAll_kb(account_number, who);
		}else {
			map.put("기업", vo.get(0));
			return map;
		}
		
		if(vo.size() == 0) {
			vo = accountInfoDao.getAccountSelectAll_kdb(account_number, who);
		}else {
			map.put("국민", vo.get(0));
			return map;
		}
		
		if(vo.size() == 0) {
			vo = accountInfoDao.getAccountSelectAll_nh(account_number, who);
		}else {
			map.put("산업",vo.get(0) );
			return map;
		}
		if(vo.size() == 0) {
			map = null;
		}else {
			map.put("농협", vo.get(0));
		}
		return map;
	}
	
	//계좌 index로 계좌 정보 가져오기
	public Map<String, Object> getAccountNumber(String account_index, String who) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		AccountInfoVO getVo = new AccountInfoVO();
		List<AccountInfoVO> voList = null;
		String bank = "";
		//하나
		voList = accountInfoDao.getAccountSelectAll_hana("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "하나";
				map.put("bank", bank);
			}
		}
		
		//농협
		voList = accountInfoDao.getAccountSelectAll_nh("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "농협";
				map.put("bank", bank);
			}
		}
		
		//기업
		voList = accountInfoDao.getAccountSelectAll_ibk("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "기업";
				map.put("bank", bank);
			}
		}
		
		//산업
		voList = accountInfoDao.getAccountSelectAll_kdb("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "산업";
				map.put("bank", bank);
			}
		}
		
		//국민
		voList = accountInfoDao.getAccountSelectAll_kb("all", who);
		for(AccountInfoVO vo : voList) {
			if(vo.getAccount_index().equals(account_index)) {
				getVo = vo;
				map.put("vo", getVo);
				bank = "국민";
				map.put("bank", bank);
			}
		}
		return map;
	}
	
	//특정 계좌 정보 가져오기 (은행과 계좌정보를 이용해서)
	public AccountInfoVO oneAccountInfo(String bank, String account_number, String who) {
		AccountInfoVO vo = null;
		
		switch (bank) {
		case "하나":
			if(accountInfoDao.getAccountSelectAll_hana(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_hana(account_number,who).get(0);
			}
			break;
			
		case "농협":
			if(accountInfoDao.getAccountSelectAll_nh(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_nh(account_number, who).get(0);
			}
			break;
			
		case "기업":
			if(accountInfoDao.getAccountSelectAll_ibk(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_ibk(account_number, who).get(0);
			}
			break;
			
		case "산업":
			if(accountInfoDao.getAccountSelectAll_kdb(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_kdb(account_number, who).get(0);
			}
			break;
			
		case "국민":
			if(accountInfoDao.getAccountSelectAll_kb(account_number, who).size() != 0) {
				vo = accountInfoDao.getAccountSelectAll_kb(account_number, who).get(0);
			}
			break;

		}
		
		
		return vo;
	}
	
	//계좌 개설
	public boolean account_create(Map<String, Object> map) {
		boolean result = accountInfoDao.account_create(map);
		return result;
		
	}
	
	//계좌 개설한지 30일 안되었으면 못만듦
	public boolean account_create_check(String member_index) {
		boolean result = accountInfoDao.account_create_check(member_index);
		return result;
	}
}
