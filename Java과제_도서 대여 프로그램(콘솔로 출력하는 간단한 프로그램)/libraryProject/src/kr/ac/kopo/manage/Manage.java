package kr.ac.kopo.manage;

public interface Manage {
	public int managerLoginCheck(String id, String pw); //�α���
	public void memInfoListPrint(); //ȸ������ ���
	public boolean bookInfoAdd(String bookName, String author, String publisher, String category, int bookQuantity); //���� �߰�
	public boolean bookInfoDelete(int bookIndex); //���� ����
	public boolean bookInfoModify(int bookIndex, int modifyInfo); //���� ����

}
