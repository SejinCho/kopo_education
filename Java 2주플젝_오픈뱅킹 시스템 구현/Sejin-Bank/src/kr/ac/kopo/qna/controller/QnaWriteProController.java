package kr.ac.kopo.qna.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.qna.service.QnaServiceImpl;
import kr.ac.kopo.qna.vo.QnaBoardFileVO;
import kr.ac.kopo.qna.vo.QnaBoardVO;
import kr.ac.kopo.util.SejinBankFileNamePolicy;

public class QnaWriteProController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaServiceImpl service = new QnaServiceImpl();
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		
		//저장될 경로
		String file_path = "C:/Users/HP/Documents/sejin-bank/qnaImages" ;
		MultipartRequest multiRequest = new MultipartRequest(
												request, //request 객체
												file_path, //저장될 서버 경로
												1024*1024*3, //파일 크기 3MB로 제한
												"utf-8", //인코딩 방식
												new SejinBankFileNamePolicy() //같은 이름의 파일명 방지 처리
		);
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String type = multiRequest.getParameter("type");
		
		//게시글 번호 받아오기
		int no = service.getQnaBoardNO();
		
		//게시글 저장
		String id = "qna" + no ;
		
		String member_id = ((MemberVO) session.getAttribute("memberVO")).getId();
		
		String title = multiRequest.getParameter("title");
		String content = multiRequest.getParameter("content");
		
		
		int re_step = 0;
		int re_level = 0;
		int reference = 0;
		
		if(type.equals("reply")) { //답글인 경우
			re_step = Integer.parseInt(multiRequest.getParameter("re_step"));
			re_level = Integer.parseInt(multiRequest.getParameter("re_level"));
			reference = Integer.parseInt(multiRequest.getParameter("reference")); 
			
			++ re_level;
			map.put("type", "reply");
			
		}else { //새글 등록
			reference = service.getQnaBoardCount("ref") + 1;
			map.put("type", "new");
		}
		
		
		QnaBoardVO qnaBoard = new QnaBoardVO();
		
		qnaBoard.setId(id);
		qnaBoard.setMember_id(member_id);
		qnaBoard.setTitle(title);
		qnaBoard.setContent(content);
		qnaBoard.setRe_level(re_level);
		qnaBoard.setRe_step(re_step);
		qnaBoard.setReference(reference);
		
				
		//첨부파일 저장
		List<QnaBoardFileVO> fileList = new ArrayList<QnaBoardFileVO>();
		
		Enumeration files = multiRequest.getFileNames(); 
		while(files.hasMoreElements()) { //데이터 존재 여부 판단
			String fileName = (String) files.nextElement();
			
			File f = multiRequest.getFile(fileName);
			if(f != null) {
				String orgn_file_name = multiRequest.getOriginalFileName(fileName);
				String file_chan_name = multiRequest.getFilesystemName(fileName);
				int file_size = (int) f.length();
				
				QnaBoardFileVO fileVo = new QnaBoardFileVO();
				fileVo.setId("qnaFile" + service.getQnaBoardFileNO());
				fileVo.setQna_board_id(id);
				fileVo.setFile_chan_name(file_chan_name);
				fileVo.setFile_path(file_path);
				fileVo.setOrgn_file_name(orgn_file_name);
				fileVo.setFile_size(file_size);
				
				fileList.add(fileVo);
				
			}
			
		}
		
		// 데이터 넣기(트랜잭션 수행해야함)
		
		map.put("qnaBoard", qnaBoard);
		
		if(fileList.size() != 0) {
			map.put("fileList", fileList);
		}
		
		
		int result = service.insert(map);
		if(result == (1 + fileList.size())) {
			request.setAttribute("qnaResult", "success");
		}
		
		
		return "redirect:/qna/qnaList.do";
	}

}
