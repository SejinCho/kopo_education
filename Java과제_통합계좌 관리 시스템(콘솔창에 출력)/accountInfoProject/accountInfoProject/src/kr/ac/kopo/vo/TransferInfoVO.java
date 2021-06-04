package kr.ac.kopo.vo;

public class TransferInfoVO {
	private String transferInfo_index;
	private String send_member_index;
	private String receive_member_index;
	private String send_account_index;
	private String receive_account_index;
	private String outside_account_number; //외부 계좌번호
	private String outside_account_bank; //외부 은행
	private int money; //입급일 때 send = receive, money + 
						//출금일 때 send = receive, money -
						//계좌이체일때 send != receive
	private String transfer_date;
	
	
	public String getTransferInfo_index() {
		return transferInfo_index;
	}
	public void setTransferInfo_index(String transferInfo_index) {
		this.transferInfo_index = transferInfo_index;
	}
	public String getSend_member_index() {
		return send_member_index;
	}
	public void setSend_member_index(String send_member_index) {
		this.send_member_index = send_member_index;
	}
	public String getReceive_member_index() {
		return receive_member_index;
	}
	public void setReceive_member_index(String receive_member_index) {
		this.receive_member_index = receive_member_index;
	}
	public String getSend_account_index() {
		return send_account_index;
	}
	public void setSend_account_index(String send_account_index) {
		this.send_account_index = send_account_index;
	}
	public String getReceive_account_index() {
		return receive_account_index;
	}
	public void setReceive_account_index(String receive_account_index) {
		this.receive_account_index = receive_account_index;
	}
	public String getOutside_account_number() {
		return outside_account_number;
	}
	public void setOutside_account_number(String outside_account_number) {
		this.outside_account_number = outside_account_number;
	}
	public String getOutside_account_bank() {
		return outside_account_bank;
	}
	public void setOutside_account_bank(String outside_account_bank) {
		this.outside_account_bank = outside_account_bank;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getTransfer_date() {
		return transfer_date;
	}
	public void setTransfer_date(String transfer_date) {
		this.transfer_date = transfer_date;
	}
	
	@Override
	public String toString() {
		return "TransferInfoVO [transferInfo_index=" + transferInfo_index + ", send_member_index=" + send_member_index
				+ ", receive_member_index=" + receive_member_index + ", send_account_index=" + send_account_index
				+ ", receive_account_index=" + receive_account_index + ", outside_account_number="
				+ outside_account_number + ", outside_account_bank=" + outside_account_bank + ", money=" + money
				+ ", transfer_date=" + transfer_date + "]";
	}
	
	
	
	
}	
