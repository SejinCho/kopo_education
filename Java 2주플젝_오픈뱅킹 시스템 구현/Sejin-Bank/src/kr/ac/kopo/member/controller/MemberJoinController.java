package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;

public class MemberJoinController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		String kakao_id = (String)session.getAttribute("kakao_id");
		String email = (String)session.getAttribute("email");
		
		session.setAttribute("kakao_id", kakao_id);
		session.setAttribute("email", email);
		
		request.setAttribute("kakao_id", kakao_id);
		request.setAttribute("email", email);
		System.out.println("¿©±â´Â join Controller : " + kakao_id + email);
		
		return "/pages/member/signUp.jsp";
	}

}
