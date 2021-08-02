package kr.ac.kopo.account.vo;

public class AccountInfoVO {
	String id;
	String member_id;
	String account_number ;
	String account_password;
	int balance;
	String nickname;
	int account_type; //계좌 타입 넣을까 뺄까
	int account_status ; // 계좌 상태(1. 정상 2. 휴면계좌 3. 거래중지)
	String reg_date;
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
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getAccount_password() {
		return account_password;
	}
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
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
	public int getAccount_type() {
		return account_type;
	}
	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
	public int getAccount_status() {
		return account_status;
	}
	public void setAccount_status(int account_status) {
		this.account_status = account_status;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "AccountInfoVO [id=" + id + ", member_id=" + member_id + ", account_number=" + account_number
				+ ", account_password=" + account_password + ", balance=" + balance + ", nickname=" + nickname
				+ ", account_type=" + account_type + ", account_status=" + account_status + ", reg_date=" + reg_date
				+ "]";
	} 
	
}
