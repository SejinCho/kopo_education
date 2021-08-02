package kr.ac.kopo.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.account.service.AccountServiceImpl;
import kr.ac.kopo.account.vo.AccountInfoVO;
import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.vo.MemberVO;

public class AccountLoopUpController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AccountServiceImpl service = new AccountServiceImpl();
		HttpSession session = request.getSession();
		
		String member_id = ((MemberVO)session.getAttribute("memberVO")).getId();
		
		List<AccountInfoVO> accountInfoList =  service.getAccountInfoList(member_id);
		
		request.setAttribute("accountInfoList", accountInfoList);
		return "/pages/account/lookUp.jsp";
	}

}
