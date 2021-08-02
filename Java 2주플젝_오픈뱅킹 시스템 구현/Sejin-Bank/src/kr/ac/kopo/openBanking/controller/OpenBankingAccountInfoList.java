package kr.ac.kopo.openBanking.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.service.MemberServiceImpl;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.openBanking.dao.OpenBankingDAOImpl;
import kr.ac.kopo.openBanking.service.OpenBankingServiceImpl;
import kr.ac.kopo.openBanking.vo.OpenBankingVO;

public class OpenBankingAccountInfoList implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = ((MemberVO) session.getAttribute("memberVO")).getId();
		
		//오픈뱅킹 서비스 동의 했는지 확인
		boolean check = new OpenBankingDAOImpl().checkTermsOfService(id);
		if(! check) { //동의 했으면 
			return "/pages/openBanking/termsOfService.jsp";
		}
		//오픈뱅킹 다른 은행 계좌 리스트 가져오기
		MemberServiceImpl memberService = new MemberServiceImpl();
		OpenBankingServiceImpl openService = new OpenBankingServiceImpl();
		
		
		String jumin_no = memberService.getJuminNo(id);
		
		Map<String, List<OpenBankingVO>> map = openService.accountInfoList(jumin_no);
		
		List<OpenBankingVO> accountInfo_J = map.get("J");
		List<OpenBankingVO> accountInfo_D = map.get("D");
		List<OpenBankingVO> accountInfo_Y = map.get("Y");
		
		
		request.setAttribute("accountInfo_J", accountInfo_J);
		request.setAttribute("accountInfo_D", accountInfo_D);
		request.setAttribute("accountInfo_Y", accountInfo_Y);
		
		
		return "/pages/openBanking/accountInfoList.jsp";
	}
}
