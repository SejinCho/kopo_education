package kr.ac.kopo.my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.conditionTran.vo.ConditionTranVO;
import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.my.service.MyServiceImpl;

public class ReserveTranInfoListController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String reserveResult = (String)session.getAttribute("reserveResult");
		
		session.removeAttribute("reserveResult");
		
		String member_id = ((MemberVO)session.getAttribute("memberVO")).getId();
		List<ConditionTranVO> conditionTranList = new MyServiceImpl().myConditionTranList(member_id);
		
		request.setAttribute("conditionTranList", conditionTranList);
		request.setAttribute("reserveResult", reserveResult);
		return "/pages/my/reserveTranInfoList.jsp";
	}
}
