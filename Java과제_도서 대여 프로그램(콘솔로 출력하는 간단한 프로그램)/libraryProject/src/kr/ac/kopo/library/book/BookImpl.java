package kr.ac.kopo.library.book;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.ac.kopo.library.FileUtil;

public class BookImpl implements Book{
	Crawler crawler = new Crawler();
	FileUtil bookFileUtil = new FileUtil("bookInfoList");
	
	//����Ʈ ����
	@Override
	public void printBestSeller() {
		System.out.println("<< ���� �ְ� ����Ʈ >>\n");
		crawler.bestseller();
	}
	
	//���� ���� ���� Ȯ�� (���ٸ� ����)
	@Override
	public boolean bookFileExists() {
		boolean result = false;
		result = bookFileUtil.fileExists();
		return result ;
	}
	
	//��ü ���� ����Ʈ ��������
	@Override
	public List<BookDTO> getBookInfoList() {
		List<BookDTO> bookInfoList = new ArrayList<BookDTO>();
		
		bookInfoList = (List<BookDTO>) bookFileUtil.fileReader();
		
		return bookInfoList;
	}
	
	//���� �ϳ� ���� ��������
	@Override
	public BookDTO getBookInfo(int bookIndex) {
		List<BookDTO> bookInfoList = getBookInfoList();
		BookDTO bookDTO = null;
		
		if(bookInfoList == null) {
			return bookDTO;
		}
		
		for(BookDTO bookInfo : bookInfoList) {
			if(bookInfo.getBookIndex() == bookIndex) {
				bookDTO = bookInfo;
				break;
			}
		}
		return bookDTO;
	}
	
	//���� ���� ��� ( �Ű������� 0�̸� ��ü ���)
	@Override
	public void bookInfoPrint(int bookIndex) {
		List<BookDTO> bookInfoList = null;
		
		if(bookIndex == 0) { //��ü ���
			bookInfoList = getBookInfoList();
		}else { //�ϳ� ���
			
			if(getBookInfo(bookIndex) != null) {
				bookInfoList = new ArrayList<BookDTO>();
				bookInfoList.add(getBookInfo(bookIndex));
			}
		}
		
		if(bookInfoList == null) {
			System.out.println();
			System.out.println("       *** ���� ������ �������� �ʽ��ϴ�. ***       ");
			return;
		}
		
		System.out.println("\n������ȣ\t������\t\t����\t\t���ǻ�\t\t�з�\t\t�������\t���� ����");
		System.out.println("===================================================================================================");

		for(BookDTO bookInfo : bookInfoList) {
			System.out.print(bookInfo.getBookIndex() + "\t\t");
			System.out.print(bookInfo.getBookName() + "\t");
			System.out.print(bookInfo.getAuthor() + "\t\t");
			System.out.print(bookInfo.getPublisher() + "\t\t");
			System.out.print(bookInfo.getCategory() + "\t\t");
			if(bookInfo.getReservationCheck() == 0) {
				System.out.print("����\t\t");
			}else {
				System.out.print("�Ұ���\t\t");
			}
			System.out.println(bookInfo.getBookQuantity());
		}
		
		
	}
	
	//���� �˻�(������ �ڵ尡 ��������)
	@Override
	public void selectBookListPrint(String keyword) {
		List<BookDTO> bookInfoList = (List<BookDTO>) bookFileUtil.fileReader();
		
		String regex = ".*" + keyword + ".*" ;
		Pattern p = Pattern.compile(regex);
		
		int cnt = 0;
		System.out.println("\n������ȣ\t������\t\t����\t\t���ǻ�\t\t�з�\t\t�������\t���� ����");
		System.out.println("===================================================================================================");
		
		if(bookInfoList == null) {
			System.out.println();
			System.out.println("       *** ���� ������ �������� �ʽ��ϴ�. ***       ");
			return;
		}
		
		for(BookDTO bookInfo : bookInfoList) {
			
			Matcher mName = p.matcher(bookInfo.getBookName());
			Matcher mAuthor = p.matcher(bookInfo.getAuthor());
			Matcher mPublisher = p.matcher(bookInfo.getPublisher());
			Matcher mCategory = p.matcher(bookInfo.getCategory());
			
			if(mName.matches() || mAuthor.matches() 
					|| mPublisher.matches() || mCategory.matches()) {
				
				System.out.print(bookInfo.getBookIndex() + "\t");
				System.out.print(bookInfo.getBookName() + "\t");
				System.out.print(bookInfo.getAuthor() + "\t\t");
				System.out.print(bookInfo.getPublisher() + "\t\t");
				System.out.print(bookInfo.getCategory() + "\t\t");
				if(bookInfo.getReservationCheck() == 0) {
					System.out.print("����\t\t");
				}else {
					System.out.print("�Ұ���\t\t");
				}
				System.out.println(bookInfo.getBookQuantity());
				
				cnt++;
			}
		}
		
		if(cnt == 0) {
			System.out.println();
			System.out.println("       *** ���� ������ �������� �ʽ��ϴ�. ***       ");
		}
		
	}
	
}
