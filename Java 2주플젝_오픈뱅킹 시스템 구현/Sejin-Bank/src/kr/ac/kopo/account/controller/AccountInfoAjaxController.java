package kr.ac.kopo.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.ac.kopo.account.service.AccountServiceImpl;
import kr.ac.kopo.account.vo.AccountInfoVO;
import kr.ac.kopo.controller.Controller;

public class AccountInfoAjaxController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AccountServiceImpl service = new AccountServiceImpl();
		String account_number = request.getParameter("account_number");
		
		//계좌정보
		AccountInfoVO accountInfo = service.getAccountInfo(account_number);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonAccountInfo = mapper.writeValueAsString(accountInfo);
		
		request.setAttribute("accountInfo", accountInfo);
		request.setAttribute("jsonAccountInfo", jsonAccountInfo);
		return "/pages/account/accountInfoAjax.jsp";
	}
}
