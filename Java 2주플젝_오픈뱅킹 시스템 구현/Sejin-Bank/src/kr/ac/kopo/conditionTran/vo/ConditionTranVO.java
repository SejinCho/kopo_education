package kr.ac.kopo.conditionTran.vo;

public class ConditionTranVO {
	private String id;
	private String member_id; 
	private String name; 
	private String type; // 예약이체:R, 자동이체:A
	private String condition_tran_date;
	private String condition_tran_time;
	private String send_account_number ;
	private String receive_account_number ;
	private String send_bank_code ;
	private String receive_bank_code ;
	private int tran_amt;
	private String my_content ;
	private String receive_content ;
	private String tran_state; //완료(S), 예약(R)
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCondition_tran_date() {
		return condition_tran_date;
	}
	public void setCondition_tran_date(String condition_tran_date) {
		this.condition_tran_date = condition_tran_date;
	}
	public String getCondition_tran_time() {
		return condition_tran_time;
	}
	public void setCondition_tran_time(String condition_tran_time) {
		this.condition_tran_time = condition_tran_time;
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
	public String getTran_state() {
		return tran_state;
	}
	public void setTran_state(String tran_state) {
		this.tran_state = tran_state;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Override
	public String toString() {
		return "ConditionTranVO [id=" + id + ", member_id=" + member_id + ", name=" + name + ", type=" + type
				+ ", condition_tran_date=" + condition_tran_date + ", condition_tran_time=" + condition_tran_time
				+ ", send_account_number=" + send_account_number + ", receive_account_number=" + receive_account_number
				+ ", send_bank_code=" + send_bank_code + ", receive_bank_code=" + receive_bank_code + ", tran_amt="
				+ tran_amt + ", my_content=" + my_content + ", receive_content=" + receive_content + ", tran_state="
				+ tran_state + "]";
	}
	
	
}
