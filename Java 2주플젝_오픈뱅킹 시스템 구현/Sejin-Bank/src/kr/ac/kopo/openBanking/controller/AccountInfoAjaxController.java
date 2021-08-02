package kr.ac.kopo.openBanking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.openBanking.service.OpenBankingServiceImpl;
import kr.ac.kopo.openBanking.vo.OpenBankingVO;

public class AccountInfoAjaxController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OpenBankingServiceImpl service = new OpenBankingServiceImpl();
		
		String bank_code = request.getParameter("bank_code");
		String account_number = request.getParameter("account_number");
		
		
		OpenBankingVO accountInfo = service.accountInfo(account_number, bank_code);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonAccountInfo = mapper.writeValueAsString(accountInfo);
		request.setAttribute("jsonAccountInfo", jsonAccountInfo);
		
		
		return "/pages/openBanking/accountInfoAjax.jsp";
	}

}
