package kr.ac.kopo.vo;

public class ManagerVO {
	private String manager_index;
	private String manager_id;
	private String manager_passwd;
	private String join_date;

	public String getManager_index() {
		return manager_index;
	}
	public void setManager_index(String manager_index) {
		this.manager_index = manager_index;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_passwd() {
		return manager_passwd;
	}
	public void setManager_passwd(String manager_passwd) {
		this.manager_passwd = manager_passwd;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	
	@Override
	public String toString() {
		return "ManagerVO [manager_index=" + manager_index + ", manager_id=" + manager_id + ", manager_passwd="
				+ manager_passwd + ", join_date=" + join_date + "]";
	}
}
