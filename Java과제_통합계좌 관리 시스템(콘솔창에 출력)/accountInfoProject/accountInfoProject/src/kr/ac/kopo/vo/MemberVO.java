package kr.ac.kopo.vo;

public class MemberVO {
	private String member_index;
	private String id ;
	private String passWd;
	private String name;
	private String phone;
	private String addr;
	private int gender;
	private String join_date;
	
	
	public String getMember_index() {
		return member_index;
	}
	public void setMember_index(String member_index) {
		this.member_index = member_index;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	
	
	@Override
	public String toString() {
		return "MemberVO [member_index=" + member_index + ", id=" + id + ", passWd=" + passWd + ", name=" + name
				+ ", phone=" + phone + ", addr=" + addr +  ", gender=" + gender + ", join_date="
				+ join_date + "]";
	}
	
	
	
	
	
	
}
