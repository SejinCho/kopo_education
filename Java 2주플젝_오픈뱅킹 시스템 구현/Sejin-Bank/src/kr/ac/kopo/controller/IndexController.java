package kr.ac.kopo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class IndexController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		
		HttpSession session = request.getSession();
		String loginCheck = (String) session.getAttribute("loginCheck"); //로그인 시 회원인지 확인(카카오로그인)
		String joinCheck = (String) session.getAttribute("joinCheck"); //회원가입 성공? 실패?
		String transferResult = (String) session.getAttribute("transferResult"); //회원가입 성공? 실패?
		String openTransferSend = (String) session.getAttribute("openTransferSend"); //오픈뱅킹 세진은행 > 다른은행 계좌이체 확인
		
		
		String accountCreateResult = (String) session.getAttribute("accountCreateResult"); //계좌개설 성공인지
		
		session.removeAttribute("loginCheck");
		session.removeAttribute("joinCheck");
		session.removeAttribute("accountCreateResult");
		session.removeAttribute("transferResult");
		session.removeAttribute("openTransferSend");
		
		request.setAttribute("accountCreateResult", accountCreateResult);
		request.setAttribute("loginCheck", loginCheck);
		request.setAttribute("joinCheck", joinCheck);
		request.setAttribute("transferResult", transferResult);
		request.setAttribute("openTransferSend", openTransferSend);
		
		System.out.println("transferResult : " + transferResult); //세진은행 계좌에서 이체 성공 여부
		System.out.println("���� ������ index loginCheck? : " + loginCheck); //회원인지 아닌지(회원아니면 회갑창)
		System.out.println("���� ������ index joinCheck? : " + joinCheck); //회원가입 성공인지
		System.out.println("accountCreateResult : " + accountCreateResult); //계좌개설 성공인지
		System.out.println("openTransferSend : " + openTransferSend); //계좌개설 성공인지
		
		return "/";
	}

}
