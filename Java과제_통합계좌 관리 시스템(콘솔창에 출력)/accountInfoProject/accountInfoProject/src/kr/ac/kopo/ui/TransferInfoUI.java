package kr.ac.kopo.ui;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.util.SessionFactory;
import kr.ac.kopo.vo.AccountInfoVO;
import kr.ac.kopo.vo.TransferInfoVO;

public class TransferInfoUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		System.out.println("=================================");
		System.out.println("           ��ü �ŷ� ���� ");
		System.out.println("=================================");
		int rownum = getInt("�ֱ� �� ���� Ȯ���ϰڽ��ϱ�? : ");
		List<TransferInfoVO> voList = transferInfoService.getTransferInfo(rownum);
		String bank = "";
		
		for(TransferInfoVO vo : voList) { 
			AccountInfoVO sendVo = new AccountInfoVO();
			AccountInfoVO receiveVo = new AccountInfoVO();
			if(vo.getSend_account_index() != null && vo.getReceive_account_index() != null && vo.getSend_account_index().equals(vo.getReceive_account_index())) { //�Ա� or ���
				
				Map<String, Object> map = accountInfoService.getAccountNumber(vo.getSend_account_index(),"me");
				sendVo = (AccountInfoVO) map.get("vo");
				bank = (String) map.get("bank");
				System.out.print("<" + bank + "����> ���� ��ȣ : " + sendVo.getAccount_number() + "(" + sendVo.getNickname() + ")");
				if(vo.getMoney() >0) {
					System.out.print(", �ݾ� : +" + vo.getMoney());
				}else if(vo.getMoney()<0) {
					System.out.print(", �ݾ� : " + vo.getMoney());
				}
				System.out.println();
				
			}else { //������ü
				if(vo.getSend_account_index() == null && vo.getReceive_member_index().equals(SessionFactory.getSessionInstance("member_id"))) { //�޴� ����� �� 
					Map<String, Object> map = accountInfoService.getAccountNumber(vo.getReceive_account_index(),"me");
					receiveVo = (AccountInfoVO) map.get("vo");
					bank = (String) map.get("bank");
					System.out.print("<" + bank + "����> ���� ��ȣ : " + receiveVo.getAccount_number() + "(" + receiveVo.getNickname() + ")");
					System.out.print(", �ݾ� : " + vo.getMoney());
					System.out.println();
					
				}else { //���� ����� �� 
					Map<String, Object> map = accountInfoService.getAccountNumber(vo.getSend_account_index(),"me");
					sendVo = (AccountInfoVO) map.get("vo");
					bank = (String) map.get("bank");
					System.out.print("<"+ bank + "����> ���� ��ȣ : " + sendVo.getAccount_number() + "(" + sendVo.getNickname()+")");
					System.out.print(", �ݾ� : -" + vo.getMoney());
					System.out.println();
				}
			}
		}
		
	}
}
