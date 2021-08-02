package kr.ac.kopo.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.qna.service.QnaServiceImpl;
import kr.ac.kopo.qna.vo.QnaBoardMemberVO;
import kr.ac.kopo.qna.vo.QnaBoardVO;

public class QnaModifyController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaServiceImpl service = new QnaServiceImpl();
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String name = request.getParameter("name");
		
		request.setAttribute("id", id);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("name", name);
		return "/pages/qna/modify.jsp";
	}

}
