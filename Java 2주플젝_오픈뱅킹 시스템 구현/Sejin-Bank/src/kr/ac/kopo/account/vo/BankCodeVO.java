package kr.ac.kopo.account.vo;

public class BankCodeVO {
	private String bank_code;
	private String bank_name;
	
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	@Override
	public String toString() {
		return "BankCodeVO [bank_code=" + bank_code + ", bank_name=" + bank_name + "]";
	}
	
}
