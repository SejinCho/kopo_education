package kr.ac.kopo.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;

public class QnaWriteController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String re_step = request.getParameter("re_step");
		String re_level = request.getParameter("re_level");
		String reference = request.getParameter("reference");
		
		request.setAttribute("type", type);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		request.setAttribute("reference", reference);
		
		return "/pages/qna/qnaWrite.jsp";
	}

}
