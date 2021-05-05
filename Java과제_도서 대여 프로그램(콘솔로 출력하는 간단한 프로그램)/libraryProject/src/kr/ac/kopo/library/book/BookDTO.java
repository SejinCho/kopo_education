package kr.ac.kopo.library.book;

import java.io.Serializable;

public class BookDTO implements Serializable{
	private int bookIndex; //���� ��ȣ(index)
	private String bookName; //������
	private String author; //����
	private String publisher ; //���ǻ�
	private String category; //�з�
	private int reservationCheck; //�뿩����(�뿩 ����: 0, �뿩 �Ұ�: 1) 
	private String registerDate ; //���� ��� ��¥
	private int bookQuantity; //���� ����
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getReservationCheck() {
		return reservationCheck;
	}
	public void setReservationCheck(int reservationCheck) {
		this.reservationCheck = reservationCheck;
	}
	public int getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(int bookIndex) {
		this.bookIndex = bookIndex;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public int getBookQuantity() {
		return bookQuantity;
	}
	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
	@Override
	public String toString() {
		return "BookDTO [bookIndex=" + bookIndex + ", bookName=" + bookName + ", author=" + author + ", publisher="
				+ publisher + ", category=" + category + ", reservationCheck=" + reservationCheck + ", registerDate="
				+ registerDate + "]";
	}
	
	
	
	
}
