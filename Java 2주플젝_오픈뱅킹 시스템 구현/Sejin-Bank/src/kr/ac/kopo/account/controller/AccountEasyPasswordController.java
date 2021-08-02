package kr.ac.kopo.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.account.dao.AccountDAOImpl;
import kr.ac.kopo.controller.Controller;

public class AccountEasyPasswordController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String member_id = request.getParameter("member_id");
		
		String easy_password = new AccountDAOImpl().getEasyPassword(member_id);
		
		request.setAttribute("easy_password", easy_password);
		return "/pages/account/easyPw.jsp";
	}
}
