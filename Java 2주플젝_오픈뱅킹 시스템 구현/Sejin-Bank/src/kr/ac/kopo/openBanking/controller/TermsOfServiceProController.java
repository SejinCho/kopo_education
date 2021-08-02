package kr.ac.kopo.openBanking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.openBanking.service.OpenBankingServiceImpl;

public class TermsOfServiceProController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String member_id = ((MemberVO) session.getAttribute("memberVO")).getId();
		
		//동의했음 그럼 member table update 
		boolean result = new OpenBankingServiceImpl().serviceAgree(member_id);
		
		
		
		
		return "redirect:/openBanking/accountInfoList.do";
	}

}
