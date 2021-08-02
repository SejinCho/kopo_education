package kr.ac.kopo.openBanking.vo;

public class OpenBankingVO {
	private String id;
	private String jumin_no;
	private String account_number ;
	private String account_password;
	private int balance;
	private String nickname;
	private int account_type; //계좌 타입 넣을까 뺄까
	private int account_status ; // 계좌 상태(1. 정상 2. 휴면계좌 3. 거래중지)
	private String bank_code;
	private String reg_date;
	private String bank_name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJumin_no() {
		return jumin_no;
	}
	public void setJumin_no(String jumin_no) {
		this.jumin_no = jumin_no;
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
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	@Override
	public String toString() {
		return "OpenBankingVO [id=" + id + ", jumin_no=" + jumin_no + ", account_number=" + account_number
				+ ", account_password=" + account_password + ", balance=" + balance + ", nickname=" + nickname
				+ ", account_type=" + account_type + ", account_status=" + account_status + ", bank_code=" + bank_code
				+ ", reg_date=" + reg_date + ", bank_name=" + bank_name + "]";
	}
	
}
