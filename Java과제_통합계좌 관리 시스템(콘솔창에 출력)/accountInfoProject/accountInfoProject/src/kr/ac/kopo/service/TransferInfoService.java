package kr.ac.kopo.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.dao.TransferInfoDAO;
import kr.ac.kopo.vo.TransferInfoVO;

public class TransferInfoService {
	private TransferInfoDAO transferInfoDao;
	
	public TransferInfoService() {
		transferInfoDao = new TransferInfoDAO();
	}
	
	//잔액 update
	public boolean balanceUpdate(Map<String, Object> map) {
		boolean result = transferInfoDao.balanceUpdate(map);
		return result;
	}
	
	//계좌이체
	public boolean transfer(Map<String, Object> map) {
		boolean result = transferInfoDao.transfer(map);
		return result ;
	}
		
	//거래내역 가져오기
	public List<TransferInfoVO> getTransferInfo(int rownum) {
		List<TransferInfoVO> voList = transferInfoDao.getTransferInfo(rownum);
		return voList;
	}
}
