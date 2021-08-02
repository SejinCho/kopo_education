package kr.ac.kopo.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.account.service.AccountServiceImpl;
import kr.ac.kopo.account.vo.AccountInfoVO;
import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.RandomNO;

public class AccountCreateProController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		AccountServiceImpl service = new AccountServiceImpl();
		HttpSession session = request.getSession();
		
		String member_id = ((MemberVO)session.getAttribute("memberVO")).getId();
		String account_password = request.getParameter("account_password");
		String nickname = request.getParameter("nickname");
		AccountInfoVO accountInfo = new AccountInfoVO();
		
		String account_number = "";
		
		//계좌 중복 확인
		do {
			account_number =  "1004" + RandomNO.getRanNo(9, 1);
		}while(! service.checkAvailAccountNum(account_number));
		
		System.out.println("account_number : " + account_number);
		
		//데이터 담기
		accountInfo.setMember_id(member_id);
		accountInfo.setAccount_number(account_number);
		accountInfo.setAccount_password(account_password);
		accountInfo.setAccount_status(1);
		accountInfo.setNickname(nickname);
		
		boolean result = service.createAccount(accountInfo);
		String accountCreateResult = "";
		
		
		String msg = "";
		if(result) { //계좌생성 성공
			accountCreateResult = "success";
			msg = "계좌 생성에 성공하였습니다.";
		}else {
			accountCreateResult = "none";
			msg = "계좌 생성에 실패하였습니다.";
		}
		session.setAttribute("accountCreateResult", accountCreateResult);
		session.setAttribute("accountCreateMsg", msg);
		return "redirect:/index.do";
	}
}
