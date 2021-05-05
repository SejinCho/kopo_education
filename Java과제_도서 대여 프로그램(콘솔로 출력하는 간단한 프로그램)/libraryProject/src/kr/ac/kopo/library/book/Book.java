package kr.ac.kopo.library.book;

import java.util.List;

public interface Book {
	public void printBestSeller(); //����Ʈ ���� ���
	public boolean bookFileExists(); //���� ���� ���� Ȯ��
	public List<BookDTO> getBookInfoList(); //��ü ���� ���� ��������
	public BookDTO getBookInfo(int bookIndex); //�ϳ��� ���� ���� ��������
	public void bookInfoPrint(int bookIndex); //���� ���� ���
	public void selectBookListPrint(String keyword); //���� �˻�
}
