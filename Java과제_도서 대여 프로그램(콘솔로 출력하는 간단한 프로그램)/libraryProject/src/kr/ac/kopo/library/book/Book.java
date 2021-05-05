package kr.ac.kopo.library.book;

import java.util.List;

public interface Book {
	public void printBestSeller(); //베스트 셀러 출력
	public boolean bookFileExists(); //도서 파일 존재 확인
	public List<BookDTO> getBookInfoList(); //전체 도서 정보 가져오기
	public BookDTO getBookInfo(int bookIndex); //하나의 도서 정보 가져오기
	public void bookInfoPrint(int bookIndex); //도서 정보 출력
	public void selectBookListPrint(String keyword); //도서 검색
}
