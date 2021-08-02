package kr.ac.kopo.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.account.service.AccountServiceImpl;
import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.vo.MemberVO;

public class AccountTransferProController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AccountServiceImpl service = new AccountServiceImpl();
		HttpSession session = request.getSession();
		String name = ((MemberVO)session.getAttribute("memberVO")).getName();
		
		request.setCharacterEncoding("utf-8");
		AccountTransferInfoVO transferInfo = new AccountTransferInfoVO();
		
		String send_account_number = request.getParameter("send_account_number");
		String receive_bank_code = request.getParameter("receive_bank_code");
		String send_bank_code = "S";
		String receive_account_number = request.getParameter("receive_account_number");
		int tran_amt = Integer.parseInt(request.getParameter("tran_amt"));
		String my_content = request.getParameter("my_content");
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
		
		boolean result = service.transferSend(transferInfo);
		if(result) {
			session.setAttribute("transferResult", "success");
			//카카오 알림 보내기
		}else {
			session.setAttribute("transferResult", "none");
		}
		
		return "redirect:/index.do";
	}

}
