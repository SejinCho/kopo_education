package kr.ac.kopo.library.book;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.ac.kopo.library.FileUtil;

public class BookImpl implements Book{
	Crawler crawler = new Crawler();
	FileUtil bookFileUtil = new FileUtil("bookInfoList");
	
	//베스트 셀러
	@Override
	public void printBestSeller() {
		System.out.println("<< 종합 주간 베스트 >>\n");
		crawler.bestseller();
	}
	
	//도서 파일 존재 확인 (없다면 생성)
	@Override
	public boolean bookFileExists() {
		boolean result = false;
		result = bookFileUtil.fileExists();
		return result ;
	}
	
	//전체 도서 리스트 가져오기
	@Override
	public List<BookDTO> getBookInfoList() {
		List<BookDTO> bookInfoList = new ArrayList<BookDTO>();
		
		bookInfoList = (List<BookDTO>) bookFileUtil.fileReader();
		
		return bookInfoList;
	}
	
	//도서 하나 정보 가져오기
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
	
	//도서 정보 출력 ( 매개변수가 0이면 전체 출력)
	@Override
	public void bookInfoPrint(int bookIndex) {
		List<BookDTO> bookInfoList = null;
		
		if(bookIndex == 0) { //전체 출력
			bookInfoList = getBookInfoList();
		}else { //하나 출력
			
			if(getBookInfo(bookIndex) != null) {
				bookInfoList = new ArrayList<BookDTO>();
				bookInfoList.add(getBookInfo(bookIndex));
			}
		}
		
		if(bookInfoList == null) {
			System.out.println();
			System.out.println("       *** 도서 정보가 존재하지 않습니다. ***       ");
			return;
		}
		
		System.out.println("\n도서번호\t도서명\t\t저자\t\t출판사\t\t분류\t\t예약상태\t남은 수량");
		System.out.println("===================================================================================================");

		for(BookDTO bookInfo : bookInfoList) {
			System.out.print(bookInfo.getBookIndex() + "\t\t");
			System.out.print(bookInfo.getBookName() + "\t");
			System.out.print(bookInfo.getAuthor() + "\t\t");
			System.out.print(bookInfo.getPublisher() + "\t\t");
			System.out.print(bookInfo.getCategory() + "\t\t");
			if(bookInfo.getReservationCheck() == 0) {
				System.out.print("가능\t\t");
			}else {
				System.out.print("불가능\t\t");
			}
			System.out.println(bookInfo.getBookQuantity());
		}
		
		
	}
	
	//도서 검색(굉장히 코드가 지저분함)
	@Override
	public void selectBookListPrint(String keyword) {
		List<BookDTO> bookInfoList = (List<BookDTO>) bookFileUtil.fileReader();
		
		String regex = ".*" + keyword + ".*" ;
		Pattern p = Pattern.compile(regex);
		
		int cnt = 0;
		System.out.println("\n도서번호\t도서명\t\t저자\t\t출판사\t\t분류\t\t예약상태\t남은 수량");
		System.out.println("===================================================================================================");
		
		if(bookInfoList == null) {
			System.out.println();
			System.out.println("       *** 도서 정보가 존재하지 않습니다. ***       ");
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
					System.out.print("가능\t\t");
				}else {
					System.out.print("불가능\t\t");
				}
				System.out.println(bookInfo.getBookQuantity());
				
				cnt++;
			}
		}
		
		if(cnt == 0) {
			System.out.println();
			System.out.println("       *** 도서 정보가 존재하지 않습니다. ***       ");
		}
		
	}
	
}
