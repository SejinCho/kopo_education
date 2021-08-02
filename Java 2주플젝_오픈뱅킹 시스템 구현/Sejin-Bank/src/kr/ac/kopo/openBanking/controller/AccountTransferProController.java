package kr.ac.kopo.openBanking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.openBanking.service.OpenBankingServiceImpl;

public class AccountTransferProController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OpenBankingServiceImpl service = new OpenBankingServiceImpl();
		
		HttpSession session = request.getSession();
		String name = ((MemberVO)session.getAttribute("memberVO")).getName();
		
		request.setCharacterEncoding("utf-8");
		
		AccountTransferInfoVO transferInfo = new AccountTransferInfoVO();
		
		String send_account_number = request.getParameter("send_account_number"); //보낸사람 계좌
		String receive_bank_code = request.getParameter("receive_bank_code"); //받는사람 은행 코드
		String send_bank_code = request.getParameter("send_bank_code"); //보낸사람 은행 코드
		String receive_account_number = request.getParameter("receive_account_number"); //받는 사람 계좌번호
		int tran_amt = Integer.parseInt(request.getParameter("tran_amt")); //거래 금액
		String my_content = request.getParameter("my_content"); //내통장
		String receive_content = request.getParameter("receive_content");
		
		if(my_content == null || my_content == "") {
			my_content = "이체";
		} else if(receive_content == null || receive_content == "") {
			receive_content = "입금";
		}
		
		transferInfo.setSend_account_number(send_account_number);
		transferInfo.setReceive_bank_code(receive_bank_code);
		transferInfo.setSend_bank_code(send_bank_code);
		transferInfo.setReceive_account_number(receive_account_number);
		transferInfo.setTran_amt(tran_amt);
		transferInfo.setMy_content(my_content);
		transferInfo.setReceive_content(receive_content);
		transferInfo.setName(name);
		
		
		boolean result = service.transferReceive(transferInfo);
		
		System.out.println("계좌이체 여부 : " + result);
		
		return "redirect:/index.do";
	}

}
