package kr.ac.kopo.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.service.MemberServiceImpl;
import kr.ac.kopo.member.vo.MemberVO;

public class MemberLoginProController implements Controller{
	
	MemberServiceImpl service = new MemberServiceImpl();
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberServiceImpl service = new MemberServiceImpl();
		String kakao_id = request.getParameter("kakao_id");
		String email = request.getParameter("email");
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		
		Map<String, String> map = new HashMap<String, String>();
		
		MemberVO member = new MemberVO();
		
		System.out.println("user_id : " + user_id);
		System.out.println("user_password : " + user_password);
		
		if(kakao_id == null || kakao_id == "") { //일반 로그인
			map.put("type", "basic");
			map.put("user_id", user_id);
			map.put("user_password", user_password);
			
		}else { //카카오 로그인
			map.put("type", "kakao");
			map.put("kakao_id", kakao_id);
		}
		
		member = service.loginCheck(map);

		if(member == null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginCheck", "none");
			session.setAttribute("kakao_id", kakao_id);
			session.setAttribute("email", email);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("memberVO", member);
		}
		
		return "redirect:/index.do";
	}
}
