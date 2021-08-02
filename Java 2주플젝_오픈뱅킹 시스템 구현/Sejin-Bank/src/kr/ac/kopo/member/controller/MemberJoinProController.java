package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.service.MemberServiceImpl;
import kr.ac.kopo.member.vo.MemberVO;

public class MemberJoinProController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		String kakao_id = request.getParameter("kakao_id");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String easy_password = request.getParameter("easy_password");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String zipcode = request.getParameter("zipcode");
		String addr1_load = request.getParameter("addr1_load");
		String addr1_jibun = request.getParameter("addr1_jibun");
		String addr2 = request.getParameter("addr2");
		String receive_sms_yn = request.getParameter("receive_sms_yn");
		String jumin_no = request.getParameter("jumin_no");
		
		
		MemberVO member = new MemberVO();
		
		if(kakao_id != null || kakao_id != "") {
			member.setKakao_id(kakao_id);
		}
		member.setName(name);
		member.setEasy_password(easy_password);
		member.setEmail(email);
		member.setPhone(phone);
		member.setGender(gender);
		member.setBirth(birth);
		member.setZipcode(zipcode);
		member.setAddr1_jibun(addr1_jibun);
		member.setAddr1_load(addr1_load);
		member.setAddr2(addr2);
		member.setReceive_sms_yn(receive_sms_yn);
		member.setJumin_no(jumin_no);
		member.setUser_id(user_id);
		member.setUser_password(user_password);
		System.out.println("����� joinPro : " +member);
		
		boolean result = new MemberServiceImpl().join(member);
		
		
		if(result) { //ȸ������ ����
			session = request.getSession();
			session.removeAttribute("loginCheck");
			session.setAttribute("memberVO", member);
			session.setAttribute("joinCheck", "success");
		}else { //ȸ������ ����
			session = request.getSession();
			session.setAttribute("joinCheck", "none");
		}
		
		
		return "redirect:/index.do";
	}

}
