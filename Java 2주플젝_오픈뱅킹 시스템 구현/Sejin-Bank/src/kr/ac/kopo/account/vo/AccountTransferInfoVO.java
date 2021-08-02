package kr.ac.kopo.account.vo;

public class AccountTransferInfoVO {
	private String id;
	
	private String account_number;
	private String send_account_number; //보낸 계좌
	private String receive_account_number; //받는 계좌
	
	private String receive_name; //받는 사람 이름
	private String send_name; //보내는 사람 이름
	
	private String bank_code;
	private String bank_name;
	private String name; //이름
	
	private String send_bank_code ; //보내는 사람 은행 코드
	private String receive_bank_code; //받는 사람 은행 코드 
	
	
	private int tran_amt; //거래 금액
	
	private String content;
	private String inout_type;
	
	private String my_content ; //내 통장 메모
	private String receive_content ; //받는 사람 통장 메모
	
	private String tran_date; //거래 날짜
	private String tran_time; //거래 시간
	private int tran_balance; //거래 후 잔액
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getSend_account_number() {
		return send_account_number;
	}
	public void setSend_account_number(String send_account_number) {
		this.send_account_number = send_account_number;
	}
	public String getReceive_account_number() {
		return receive_account_number;
	}
	public void setReceive_account_number(String receive_account_number) {
		this.receive_account_number = receive_account_number;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getSend_bank_code() {
		return send_bank_code;
	}
	public void setSend_bank_code(String send_bank_code) {
		this.send_bank_code = send_bank_code;
	}
	public String getReceive_bank_code() {
		return receive_bank_code;
	}
	public void setReceive_bank_code(String receive_bank_code) {
		this.receive_bank_code = receive_bank_code;
	}
	public int getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(int tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMy_content() {
		return my_content;
	}
	public void setMy_content(String my_content) {
		this.my_content = my_content;
	}
	public String getReceive_content() {
		return receive_content;
	}
	public void setReceive_content(String receive_content) {
		this.receive_content = receive_content;
	}
	public String getTran_date() {
		return tran_date;
	}
	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}
	public String getTran_time() {
		return tran_time;
	}
	public void setTran_time(String tran_time) {
		this.tran_time = tran_time;
	}
	public int getTran_balance() {
		return tran_balance;
	}
	public void setTran_balance(int tran_balance) {
		this.tran_balance = tran_balance;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getInout_type() {
		return inout_type;
	}
	public void setInout_type(String inout_type) {
		this.inout_type = inout_type;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AccountTransferInfoVO [id=" + id + ", account_number=" + account_number + ", send_account_number="
				+ send_account_number + ", receive_account_number=" + receive_account_number + ", receive_name="
				+ receive_name + ", send_name=" + send_name + ", bank_code=" + bank_code + ", bank_name=" + bank_name
				+ ", name=" + name + ", send_bank_code=" + send_bank_code + ", receive_bank_code=" + receive_bank_code
				+ ", tran_amt=" + tran_amt + ", content=" + content + ", inout_type=" + inout_type + ", my_content="
				+ my_content + ", receive_content=" + receive_content + ", tran_date=" + tran_date + ", tran_time="
				+ tran_time + ", tran_balance=" + tran_balance + "]";
	}
	
	
	
}
