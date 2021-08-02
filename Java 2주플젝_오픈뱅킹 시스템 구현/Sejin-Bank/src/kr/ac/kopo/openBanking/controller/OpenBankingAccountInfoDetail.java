package kr.ac.kopo.openBanking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.service.MemberServiceImpl;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.openBanking.service.OpenBankingServiceImpl;
import kr.ac.kopo.openBanking.vo.OpenBankingVO;

public class OpenBankingAccountInfoDetail implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전체 계좌 정보
		MemberServiceImpl memberService = new MemberServiceImpl();
		OpenBankingServiceImpl openService = new OpenBankingServiceImpl();
		
		HttpSession session = request.getSession();
		
		String id = ((MemberVO) session.getAttribute("memberVO")).getId();
		String jumin_no = memberService.getJuminNo(id);
		
		Map<String, List<OpenBankingVO>> map = openService.accountInfoList(jumin_no);
		
		List<OpenBankingVO> accountInfo_J = map.get("J");
		List<OpenBankingVO> accountInfo_D = map.get("D");
		List<OpenBankingVO> accountInfo_Y = map.get("Y");
		
		List<OpenBankingVO> accountInfoList = new ArrayList<OpenBankingVO>();
		accountInfoList.addAll(accountInfo_J);
		accountInfoList.addAll(accountInfo_D);
		accountInfoList.addAll(accountInfo_Y);
		
		String account_number = request.getParameter("account_number");
		String bank_code = request.getParameter("bank_code");
		
		request.setAttribute("accountInfoList", accountInfoList);
		
		request.setAttribute("account_number", account_number);
		request.setAttribute("bank_code", bank_code);
		
		return "/pages/openBanking/detail.jsp";
	}

}
