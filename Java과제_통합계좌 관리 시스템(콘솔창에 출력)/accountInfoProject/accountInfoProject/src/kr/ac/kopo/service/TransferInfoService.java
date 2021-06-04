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
	
	//�ܾ� update
	public boolean balanceUpdate(Map<String, Object> map) {
		boolean result = transferInfoDao.balanceUpdate(map);
		return result;
	}
	
	//������ü
	public boolean transfer(Map<String, Object> map) {
		boolean result = transferInfoDao.transfer(map);
		return result ;
	}
		
	//�ŷ����� ��������
	public List<TransferInfoVO> getTransferInfo(int rownum) {
		List<TransferInfoVO> voList = transferInfoDao.getTransferInfo(rownum);
		return voList;
	}
}
