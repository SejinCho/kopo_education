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
		System.out.println("           전체 거래 내역 ");
		System.out.println("=================================");
		int rownum = getInt("최근 몇 개를 확인하겠습니까? : ");
		List<TransferInfoVO> voList = transferInfoService.getTransferInfo(rownum);
		String bank = "";
		
		for(TransferInfoVO vo : voList) { 
			AccountInfoVO sendVo = new AccountInfoVO();
			AccountInfoVO receiveVo = new AccountInfoVO();
			if(vo.getSend_account_index() != null && vo.getReceive_account_index() != null && vo.getSend_account_index().equals(vo.getReceive_account_index())) { //입금 or 출금
				
				Map<String, Object> map = accountInfoService.getAccountNumber(vo.getSend_account_index(),"me");
				sendVo = (AccountInfoVO) map.get("vo");
				bank = (String) map.get("bank");
				System.out.print("<" + bank + "은행> 계좌 번호 : " + sendVo.getAccount_number() + "(" + sendVo.getNickname() + ")");
				if(vo.getMoney() >0) {
					System.out.print(", 금액 : +" + vo.getMoney());
				}else if(vo.getMoney()<0) {
					System.out.print(", 금액 : " + vo.getMoney());
				}
				System.out.println();
				
			}else { //계좌이체
				if(vo.getSend_account_index() == null && vo.getReceive_member_index().equals(SessionFactory.getSessionInstance("member_id"))) { //받는 사람이 나 
					Map<String, Object> map = accountInfoService.getAccountNumber(vo.getReceive_account_index(),"me");
					receiveVo = (AccountInfoVO) map.get("vo");
					bank = (String) map.get("bank");
					System.out.print("<" + bank + "은행> 계좌 번호 : " + receiveVo.getAccount_number() + "(" + receiveVo.getNickname() + ")");
					System.out.print(", 금액 : " + vo.getMoney());
					System.out.println();
					
				}else { //보낸 사람이 나 
					Map<String, Object> map = accountInfoService.getAccountNumber(vo.getSend_account_index(),"me");
					sendVo = (AccountInfoVO) map.get("vo");
					bank = (String) map.get("bank");
					System.out.print("<"+ bank + "은행> 계좌 번호 : " + sendVo.getAccount_number() + "(" + sendVo.getNickname()+")");
					System.out.print(", 금액 : -" + vo.getMoney());
					System.out.println();
				}
			}
		}
		
	}
}
