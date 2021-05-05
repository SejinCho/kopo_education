package kr.ac.kopo.library.member;

import java.util.List;

public interface Member {
	public boolean fileExists();
	
	//�α���
	public int login(String id, String pw); //�α��� üũ
	public MemberDTO getMemInfo(String id); //ȸ������ ��������
	
	//ȸ������
	public boolean isValidEmail(String email); //�̸��� ���� Ȯ��
	public boolean emailCheck(String newEmail); //�̹� ���Ե� �̸����� �ƴ��� Ȯ��
	public void mailSend(String email); //�̸��� ������ȣ ����
	public boolean emailAuth(int inputNum); //������ȣ ��ġ ����
	public boolean idCheck(String id); //���̵� �ߺ� Ȯ�� 
	public boolean confirmPW(String pw, String repw); //��� ��Ȯ��
	public boolean memInfoWrite(String name, String email, String id, String pw, int gender); //ȸ�� ���� ����
	
	public List<MemberDTO> getMemInfoList(); //ȸ������ ��������
	public void myInfoPrint(String id); //Ư�� ȸ�� ���� ���
	public boolean updatePW(String id, String updatePW); //��й�ȣ ����
	
	
}
