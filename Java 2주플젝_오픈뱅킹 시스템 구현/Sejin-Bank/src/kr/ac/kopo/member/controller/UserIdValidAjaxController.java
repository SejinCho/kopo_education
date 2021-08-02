package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.service.MemberServiceImpl;

public class UserIdValidAjaxController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		boolean result = new MemberServiceImpl().vaildUserId(user_id);
		
		request.setAttribute("result", result);
		return "/pages/member/userIdValidAjax.jsp";
	}
}
