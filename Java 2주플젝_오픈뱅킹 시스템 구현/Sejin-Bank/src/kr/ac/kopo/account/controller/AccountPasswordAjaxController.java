package kr.ac.kopo.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.account.service.AccountServiceImpl;
import kr.ac.kopo.controller.Controller;

public class AccountPasswordAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String account_number = request.getParameter("account_number");
		String account_password = new AccountServiceImpl().getAccountPassword(account_number);
		
		request.setAttribute("account_password", account_password);
		return "/pages/account/accountPasswordAjax.jsp";
	}

}
