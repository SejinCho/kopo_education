package kr.ac.kopo.openBanking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.service.MemberServiceImpl;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.openBanking.service.OpenBankingServiceImpl;
import kr.ac.kopo.openBanking.vo.OpenBankingVO;

public class AccountInfoListAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberServiceImpl memberService = new MemberServiceImpl();
		OpenBankingServiceImpl openService = new OpenBankingServiceImpl();
		
		HttpSession session = request.getSession();
		String id = ((MemberVO) session.getAttribute("memberVO")).getId();
		String jumin_no = memberService.getJuminNo(id);
		
		String bank_code = request.getParameter("bank_code");
		List<OpenBankingVO> accountInfoList = openService.accountInfoList_code(jumin_no, bank_code);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonAccountInfoList = mapper.writeValueAsString(accountInfoList);
		
		request.setAttribute("jsonAccountInfoList", jsonAccountInfoList);
		
		return "/pages/openBanking/accountInfoListAjax.jsp";
	}

}
