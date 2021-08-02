package kr.ac.kopo.qna.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.qna.dao.QnaDAOImpl;
import kr.ac.kopo.qna.vo.PagingVO;
import kr.ac.kopo.qna.vo.QnaBoardFileVO;
import kr.ac.kopo.qna.vo.QnaBoardMemberVO;
import kr.ac.kopo.qna.vo.QnaBoardVO;

public class QnaServiceImpl implements QnaService {
	
	QnaDAOImpl dao = new QnaDAOImpl();
	
	//BOARD 시퀀스 값
	@Override
	public int getQnaBoardNO() {
		return dao.getQnaBoardNO();
	}
	
	@Override
	public int getQnaBoardFileNO() {
		return dao.getQnaBoardFileNO();
	}
	
	//글 개수 가져오기(all) or reference 개수 가져오기(ref)
	@Override
	public int getQnaBoardCount(String type) {
		return dao.getQnaBoardCount(type);
	}
	
	//insert (게시글, 파일)
	@Override
	public int insert(Map<Object, Object> map) {
		return dao.insert(map); 
	}
	
	//board List
	@Override
	public List<QnaBoardMemberVO> getQnaBoardList() {
		return dao.getQnaBoardList();
	}
	
	//게시판 하나 글 가져오기
	@Override
	public QnaBoardMemberVO selectOneBoard(String id) {
		return dao.selectOneBoard(id);
	}
	
	//하나 게시글에 대한 파일
	@Override
	public List<QnaBoardFileVO> selectOneBoardFile(String qna_board_id) {
		return dao.selectOneBoardFile(qna_board_id);
	}
	
	//페이징 처리 후 게시글 가져오기
	@Override
	public List<QnaBoardMemberVO> getQnaBoardPaging(PagingVO pagingVo) {
		return dao.getQnaBoardPaging(pagingVo);
	}
}
