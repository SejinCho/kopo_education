package kr.ac.kopo.conditionTran.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.conditionTran.service.ConditionTranServiceImpl;
import kr.ac.kopo.conditionTran.vo.ConditionTranVO;
import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.vo.MemberVO;

public class ReserveTransferProController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String condition_tran_date = request.getParameter("condition_tran_date");
		String condition_tran_time = request.getParameter("condition_tran_time");
		
		System.out.println("condition_tran_date : " + condition_tran_date);
		System.out.println("condition_tran_time : " + condition_tran_time);
		
		//예약이체
		HttpSession session = request.getSession();
		String member_id = ((MemberVO)session.getAttribute("memberVO")).getId();
		String name = ((MemberVO)session.getAttribute("memberVO")).getName();
		String send_account_number = request.getParameter("send_account_number"); //보낸사람 계좌
		String receive_bank_code = request.getParameter("receive_bank_code"); //받는사람 은행 코드
		String send_bank_code = request.getParameter("send_bank_code"); //보낸사람 은행 코드
		String receive_account_number = request.getParameter("receive_account_number"); //받는 사람 계좌번호
		int tran_amt = Integer.parseInt(request.getParameter("tran_amt")); //거래 금액
		String my_content = request.getParameter("my_content"); //내통장
		String receive_content = request.getParameter("receive_content");
		
		
		
		if(my_content == null || my_content == "") {
			my_content = "이체";
		} else if(receive_content == null || receive_content == "") {
			receive_content = "입금";
		}
		
		ConditionTranVO conditionTran = new ConditionTranVO();
		conditionTran.setSend_account_number(send_account_number);
		conditionTran.setReceive_bank_code(receive_bank_code);
		conditionTran.setSend_bank_code(send_bank_code);
		conditionTran.setReceive_account_number(receive_account_number);
		conditionTran.setTran_amt(tran_amt);
		conditionTran.setMy_content(my_content);
		conditionTran.setReceive_content(receive_content);
		conditionTran.setName(name);
		conditionTran.setCondition_tran_date(condition_tran_date);
		conditionTran.setCondition_tran_time(condition_tran_time);
		conditionTran.setMember_id(member_id);
		
		boolean result = new ConditionTranServiceImpl().insertReserveTran(conditionTran);
		if(result == true) {
			session.setAttribute("reserveResult", "success");
		}
		return "redirect:/my/reserveTranInfo.do";
	}

}
