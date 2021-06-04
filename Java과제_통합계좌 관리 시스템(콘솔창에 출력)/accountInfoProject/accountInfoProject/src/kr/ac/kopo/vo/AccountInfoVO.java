package kr.ac.kopo.vo;

public class AccountInfoVO {
	private String account_index; 
	private String member_index;
	private String account_number;
	private String account_passwd;
	private int balance;
	private String nickname;
	private String registration_date;
	private int account_type; //하나은행만 있음
	
	public String getAccount_index() {
		return account_index;
	}
	public void setAccount_index(String account_index) {
		this.account_index = account_index;
	}
	public String getMember_index() {
		return member_index;
	}
	public void setMember_index(String member_index) {
		this.member_index = member_index;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getAccount_passwd() {
		return account_passwd;
	}
	public void setAccount_passwd(String account_passwd) {
		this.account_passwd = account_passwd;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	
	public int getAccount_type() {
		return account_type;
	}
	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
	
	
	@Override
	public String toString() {
		return "AccountInfoVO [account_index=" + account_index + ", member_index=" + member_index + ", account_number="
				+ account_number + ", account_passwd=" + account_passwd + ", balance=" + balance + ", nickname="
				+ nickname + ", registration_date=" + registration_date + ", account_type=" + account_type + "]";
	}
	
} 
