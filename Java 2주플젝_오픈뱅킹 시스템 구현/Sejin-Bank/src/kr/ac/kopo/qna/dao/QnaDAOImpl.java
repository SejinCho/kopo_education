package kr.ac.kopo.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.kopo.qna.vo.PagingVO;
import kr.ac.kopo.qna.vo.QnaBoardFileVO;
import kr.ac.kopo.qna.vo.QnaBoardMemberVO;
import kr.ac.kopo.qna.vo.QnaBoardVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;

public class QnaDAOImpl implements QnaDAO {
	
	//qna board 시퀀스 가져오기 
	@Override
	public int getQnaBoardNO() {
		int no = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("select sj_seq_qna_board_id.nextval as no from dual");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt("no");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return no;
	}
	
	//파일 시퀀스
	@Override
	public int getQnaBoardFileNO() {
		int no = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("select sj_seq_qna_board_file_id.nextval as no from dual");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt("no");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return no;
	}
	
	//글 개수 가져오기(all) or reference 개수 가져오기(ref)
	@Override
	public int getQnaBoardCount(String type) {
		int cnt = 0;
		StringBuilder sql = new StringBuilder();
		if(type.equals("all")) {
			sql.append("select count(*) as cnt from sj_qna_board");
		} else if(type.equals("ref")) {
			sql.append("select board_ref.nextval as cnt from dual");
		}
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	//글 넣기(트랜잭션)
	@Override
	public int insert(Map<Object, Object> map) {
		int result = 0;
		String type = (String) map.get("type");
		
		StringBuilder sql = new StringBuilder();
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		//게시판 글 넣기
		QnaBoardVO board = (QnaBoardVO) map.get("qnaBoard");
		
		if(type.equals("new")) { //새글일 경우 
			sql.append("insert into sj_qna_board(id, member_id, title, content, reference, re_level, re_step) ");
			sql.append("values(?, ?, ?, ?, ? ,? ,?)");
		} else { //새글이 아니면 re_step 계산해서 사용
			sql.append("insert into sj_qna_board(id, member_id, title, content, reference, re_level, re_step) ");
			sql.append("values(?, ?, ?, ?, ? ,? ,(select max(re_step) from sj_qna_board group by reference having reference = ?) +1 ) ");
		}
		
		
		try {
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			conn.setAutoCommit(false);
			
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getMember_id());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			pstmt.setInt(5, board.getReference());
			pstmt.setInt(6, board.getRe_level());
			if(type.equals("new")) {
				pstmt.setInt(7, board.getRe_step());
			} else {
				pstmt.setInt(7, board.getReference());
			}
			
			result = pstmt.executeUpdate(); //성공 : 1 , 실패 : 0
			
			System.out.println("dao : " +map.containsKey("fileList") );
			
			if(map.containsKey("fileList")) {
				//파일 넣기
				List<QnaBoardFileVO> fileVoList = (List<QnaBoardFileVO>) map.get("fileList");
				
				StringBuilder sql2 = new StringBuilder();
				
				sql2.append("insert into sj_qna_board_file(id, qna_board_id ,file_chan_name, file_path, orgn_file_name, file_size) ");
				sql2.append("values(?, ?,?,?,?,?)");
				
				pstmt2 = conn.prepareStatement(sql2.toString());
				
				for(QnaBoardFileVO fileVo : fileVoList) {
					
					pstmt2.setString(1, fileVo.getId());
					pstmt2.setString(2, fileVo.getQna_board_id());
					pstmt2.setString(3, fileVo.getFile_chan_name());
					pstmt2.setString(4, fileVo.getFile_path());
					pstmt2.setString(5, fileVo.getOrgn_file_name());
					pstmt2.setInt(6, fileVo.getFile_size());
					pstmt2.addBatch();
					
					
				}
				pstmt2.executeBatch();
			}
			
			
		} catch (Exception e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
				if(pstmt2 != null)
					pstmt2.close();
				JDBCClose.close(conn, pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	//qna list 가져오기
	@Override
	public List<QnaBoardMemberVO> getQnaBoardList() {
		List<QnaBoardMemberVO> boardList = new ArrayList<QnaBoardMemberVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select b.id, b.title, m.name, b.view_cnt, to_char(b.reg_date, 'yyyy-mm-dd') as r_date, ");
		sql.append("b.reference, b.re_step, b.re_level  from sj_qna_board b, sj_member m ");
		sql.append("where b.member_id = m.id order by b.reference desc, b.re_level, b.re_step desc");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaBoardMemberVO boardMember = new QnaBoardMemberVO();
				boardMember.setId(rs.getString("id"));
				boardMember.setTitle(rs.getString("title"));
				boardMember.setName(rs.getString("name"));
				boardMember.setView_cnt(rs.getInt("view_cnt"));
				boardMember.setReg_date(rs.getString("r_date"));
				boardMember.setReference(rs.getInt("reference"));
				boardMember.setRe_level(rs.getInt("re_level"));
				boardMember.setRe_step(rs.getInt("re_step"));
				
				boardList.add(boardMember);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
	
	//게시판 하나 글 가져오기
	@Override
	public QnaBoardMemberVO selectOneBoard(String id) {
		QnaBoardMemberVO board = new QnaBoardMemberVO();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select b.id, b.member_id, b.content, b.title, m.name, b.view_cnt, to_char(b.reg_date, 'yyyy-mm-dd') as reg_date, ");
		sql.append("b.reference, b.re_step, b.re_level  from sj_qna_board b, sj_member m ");
		sql.append("where b.member_id = m.id and b.id = ?");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board.setId(rs.getString("id"));
				board.setMember_id(rs.getString("member_id"));
				board.setTitle(rs.getString("title"));
				board.setName(rs.getString("name"));
				board.setContent(rs.getString("content"));
				board.setView_cnt(rs.getInt("view_cnt"));
				board.setReg_date(rs.getString("reg_date"));
				board.setReference(rs.getInt("reference"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	//하나 게시판에 대한 file들 가져오기
	@Override
	public List<QnaBoardFileVO> selectOneBoardFile(String qna_board_id) {
		List<QnaBoardFileVO> fileList = new ArrayList<QnaBoardFileVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from sj_qna_board_file where qna_board_id = ? ");
		
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			pstmt.setString(1, qna_board_id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaBoardFileVO file = new QnaBoardFileVO();
				file.setId(rs.getString("id"));
				file.setQna_board_id(rs.getString("qna_board_id"));
				file.setFile_chan_name(rs.getString("file_chan_name"));
				file.setFile_path(rs.getString("file_path"));
				file.setOrgn_file_name(rs.getString("orgn_file_name"));
				file.setFile_size(rs.getInt("file_size"));
				fileList.add(file);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileList;
	}
	
	//페이징 처리 후 게시글 조회
	@Override
	public List<QnaBoardMemberVO> getQnaBoardPaging(PagingVO pagingVo) {
		List<QnaBoardMemberVO> boardList = new ArrayList<QnaBoardMemberVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum rn, a.* from  ");
		sql.append("(select b.id, b.title, m.name, b.view_cnt, to_char(b.reg_date, 'yyyy-mm-dd') as r_date, ");
		sql.append("b.reference, b.re_step, b.re_level  from sj_qna_board b, sj_member m ");
		sql.append("where b.member_id = m.id order by b.reference desc, b.re_level, b.re_step desc) a) ");
		sql.append("where rn between ? and ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, pagingVo.getStart());
			pstmt.setInt(2, pagingVo.getEnd());
			
			ResultSet rs = pstmt.executeQuery();
			//여기부터 수정
			while(rs.next()) {
				QnaBoardMemberVO boardMember = new QnaBoardMemberVO();
				boardMember.setId(rs.getString("id"));
				boardMember.setTitle(rs.getString("title"));
				boardMember.setName(rs.getString("name"));
				boardMember.setView_cnt(rs.getInt("view_cnt"));
				boardMember.setReg_date(rs.getString("r_date"));
				boardMember.setReference(rs.getInt("reference"));
				boardMember.setRe_level(rs.getInt("re_level"));
				boardMember.setRe_step(rs.getInt("re_step"));
				
				boardList.add(boardMember);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
}
