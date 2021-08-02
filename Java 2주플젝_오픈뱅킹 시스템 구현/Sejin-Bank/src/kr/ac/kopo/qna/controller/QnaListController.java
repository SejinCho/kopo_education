package kr.ac.kopo.qna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.qna.service.QnaServiceImpl;
import kr.ac.kopo.qna.vo.PagingVO;
import kr.ac.kopo.qna.vo.QnaBoardMemberVO;

public class QnaListController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaServiceImpl service = new QnaServiceImpl();
		
		List<QnaBoardMemberVO> boardList = service.getQnaBoardList();
		request.setAttribute("boardList", boardList);
		
		
		
		//페이징 처리
		
		int total = service.getQnaBoardCount("all");
		String nowPage = request.getParameter("nowPage");
		String cntPerPage = request.getParameter("cntPerPage");
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "5";
		}
			
		PagingVO vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		request.setAttribute("paging", vo);
		request.setAttribute("viewAll", service.getQnaBoardPaging(vo));
		
		return "/pages/qna/qnaList.jsp";
	}
}
