package kr.ac.kopo.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.ac.kopo.account.service.AccountServiceImpl;
import kr.ac.kopo.account.vo.AccountTransferInfoVO;
import kr.ac.kopo.controller.Controller;

public class AccountTransferInfoAjax implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//계좌 거래 내역 가져오기
		AccountServiceImpl service = new AccountServiceImpl();
		
		//변수
		String account_number = request.getParameter("account_number");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String inout_type = request.getParameter("inout_type");
		String order = request.getParameter("order");
		
		//변수 담기
		Map<String, String> map = new HashMap<String, String>();
		map.put("account_number", account_number);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		map.put("inout_type", inout_type);
		map.put("order", order);
		
		
		List<AccountTransferInfoVO> transferInfoList = service.getAccountTransferInfo(map);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonTransferInfoList = mapper.writeValueAsString(transferInfoList);
		
		request.setAttribute("transferInfoList", jsonTransferInfoList);
		return "/pages/account/transferInfoAjax.jsp";
	}
}
