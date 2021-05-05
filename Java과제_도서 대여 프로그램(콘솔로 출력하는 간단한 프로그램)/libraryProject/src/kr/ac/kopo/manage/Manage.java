package kr.ac.kopo.manage;

public interface Manage {
	public int managerLoginCheck(String id, String pw); //로그인
	public void memInfoListPrint(); //회원정보 출력
	public boolean bookInfoAdd(String bookName, String author, String publisher, String category, int bookQuantity); //도서 추가
	public boolean bookInfoDelete(int bookIndex); //도서 삭제
	public boolean bookInfoModify(int bookIndex, int modifyInfo); //도서 수정

}
