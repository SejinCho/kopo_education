package kr.ac.kopo.qna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.qna.service.QnaServiceImpl;
import kr.ac.kopo.qna.vo.QnaBoardFileVO;
import kr.ac.kopo.qna.vo.QnaBoardMemberVO;

public class QnaDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		QnaServiceImpl service = new QnaServiceImpl();
		
		QnaBoardMemberVO board = service.selectOneBoard(id);
		List<QnaBoardFileVO> fileList = service.selectOneBoardFile(id);
		
		
		if(fileList.size() != 0) {
			request.setAttribute("fileList", fileList);
		}
		request.getAttribute("id");
		request.setAttribute("board", board);
		return "/pages/qna/qnaDetail.jsp";
	}

}
