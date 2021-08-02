package kr.ac.kopo.qna.vo;

public class QnaBoardMemberVO {
	private String id;
	private String member_id;
	private String title;
	private String content;
	private int view_cnt;
	private String reg_date ;
	private int reference ; //그룹화 아이디
	private int re_step ; //글 순서 
	private int re_level ; //글 레벨
	private String name ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "QnaBoardMemberVO [id=" + id + ", member_id=" + member_id + ", title=" + title + ", content=" + content
				+ ", view_cnt=" + view_cnt + ", reg_date=" + reg_date + ", reference=" + reference + ", re_step="
				+ re_step + ", re_level=" + re_level + ", name=" + name + "]";
	}
	
	
}
