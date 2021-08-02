package kr.ac.kopo.qna.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.qna.vo.PagingVO;
import kr.ac.kopo.qna.vo.QnaBoardFileVO;
import kr.ac.kopo.qna.vo.QnaBoardMemberVO;

public interface QnaService {
	public int getQnaBoardNO(); //시퀀스값 가져오기
	public int insert(Map<Object, Object> map);
	public int getQnaBoardFileNO(); //파일 시퀀스 
	public List<QnaBoardMemberVO> getQnaBoardList(); //board 리스트
	public QnaBoardMemberVO selectOneBoard(String id); //게시판 하나 글 가져오기
	public List<QnaBoardFileVO> selectOneBoardFile(String qna_board_id); //하나 게시판에 대한 file들 가져오기
	public int getQnaBoardCount(String type);//글 개수 가져오기(all) or reference 개수 가져오기(ref)
	
	public List<QnaBoardMemberVO> getQnaBoardPaging(PagingVO pagingVo) ; //페이징 처리 후 게시글 조회 
}
