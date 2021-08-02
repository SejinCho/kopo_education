package kr.ac.kopo.qna.vo;

public class QnaBoardFileVO {
	private String id;
	private String qna_board_id;
	private String file_chan_name ;
	private String file_path ;
	private String orgn_file_name ;
	private int file_size ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQna_board_id() {
		return qna_board_id;
	}
	public void setQna_board_id(String qna_board_id) {
		this.qna_board_id = qna_board_id;
	}
	public String getFile_chan_name() {
		return file_chan_name;
	}
	public void setFile_chan_name(String file_chan_name) {
		this.file_chan_name = file_chan_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getOrgn_file_name() {
		return orgn_file_name;
	}
	public void setOrgn_file_name(String orgn_file_name) {
		this.orgn_file_name = orgn_file_name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	
	
	@Override
	public String toString() {
		return "QnaBoardFileVO [id=" + id + ", qna_board_id=" + qna_board_id + ", file_chan_name=" + file_chan_name
				+ ", file_path=" + file_path + ", orgn_file_name=" + orgn_file_name + ", file_size=" + file_size + "]";
	}
	
}
