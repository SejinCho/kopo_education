package kr.ac.kopo.library.member;

import java.io.Serializable;
import java.util.Date;

public class MemberDTO implements Serializable{
	private String name;
	private String email;
	private String id;
	private String pw;
	private int gender; //³²1, ¿©2
	private String joinDate ;
	
	public MemberDTO() {
		super();
	}
	
	public MemberDTO(String name, String email, String id, String pw, int gender, String joinDate) {
		super();
		this.name = name;
		this.email = email;
		this.id = id;
		this.pw = pw;
		this.gender = gender;
		this.joinDate = joinDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "MemberDTO [name=" + name + ", email=" + email + ", id=" + id + ", pw=" + pw + ", gender=" + gender
				+ ", joinDate=" + joinDate + "]";
	}

	

	

	
	
	
}
