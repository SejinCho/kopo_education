package kr.ac.kopo.member.vo;

public class MemberVO {
	private String id ;
	private String user_id;
	private String user_password;
	private String name ;
	private String easy_password;
	private String email;
	private String kakao_id;
	private String phone ;
	private String jumin_no ;
	private String gender ;
	private String birth;
	private String zipcode;
	private String addr1_load; //���θ��ּ�
	private String addr1_jibun; //�����ּ�
	private String addr2; //���ּ�
	private String receive_sms_yn;
	private String privacy_agree_yn;
	private String privacy_agree_date;
	private String reg_date;
	private String withdrawal_date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEasy_password() {
		return easy_password;
	}
	public void setEasy_password(String easy_password) {
		this.easy_password = easy_password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKakao_id() {
		return kakao_id;
	}
	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJumin_no() {
		return jumin_no;
	}
	public void setJumin_no(String jumin_no) {
		this.jumin_no = jumin_no;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1_load() {
		return addr1_load;
	}
	public void setAddr1_load(String addr1_load) {
		this.addr1_load = addr1_load;
	}
	public String getAddr1_jibun() {
		return addr1_jibun;
	}
	public void setAddr1_jibun(String addr1_jibun) {
		this.addr1_jibun = addr1_jibun;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getReceive_sms_yn() {
		return receive_sms_yn;
	}
	public void setReceive_sms_yn(String receive_sms_yn) {
		this.receive_sms_yn = receive_sms_yn;
	}
	public String getPrivacy_agree_yn() {
		return privacy_agree_yn;
	}
	public void setPrivacy_agree_yn(String privacy_agree_yn) {
		this.privacy_agree_yn = privacy_agree_yn;
	}
	public String getPrivacy_agree_date() {
		return privacy_agree_date;
	}
	public void setPrivacy_agree_date(String privacy_agree_date) {
		this.privacy_agree_date = privacy_agree_date;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getWithdrawal_date() {
		return withdrawal_date;
	}
	public void setWithdrawal_date(String withdrawal_date) {
		this.withdrawal_date = withdrawal_date;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", user_id=" + user_id + ", user_password=" + user_password + ", name=" + name
				+ ", easy_password=" + easy_password + ", email=" + email + ", kakao_id=" + kakao_id + ", phone="
				+ phone + ", jumin_no=" + jumin_no + ", gender=" + gender + ", birth=" + birth + ", zipcode=" + zipcode
				+ ", addr1_load=" + addr1_load + ", addr1_jibun=" + addr1_jibun + ", addr2=" + addr2
				+ ", receive_sms_yn=" + receive_sms_yn + ", privacy_agree_yn=" + privacy_agree_yn
				+ ", privacy_agree_date=" + privacy_agree_date + ", reg_date=" + reg_date + ", withdrawal_date="
				+ withdrawal_date + "]";
	}
	
	
	
}
