package kr.ac.kopo.library.reservation;

import java.io.Serializable;

public class ReservationDTO implements Serializable{
	private int reservationIndex;
	private String id; //대여한 사람 id
	private int bookIndex; //책 index
	private String reservationDate ; //대여한 날짜
	private String returnDate; //도서 반납 날짜
	private int lateFee; //연체료
	
	public int getReservationIndex() {
		return reservationIndex;
	}
	public void setReservationIndex(int reservationIndex) {
		this.reservationIndex = reservationIndex;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(int bookIndex) {
		this.bookIndex = bookIndex;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getLateFee() {
		return lateFee;
	}
	public void setLateFee(int lateFee) {
		this.lateFee = lateFee;
	}
	
	
	@Override
	public String toString() {
		return "ReservationDTO [reservationIndex=" + reservationIndex + ", id=" + id + ", bookIndex=" + bookIndex
				+ ", reservationDate=" + reservationDate + ", returnDate=" + returnDate + ", lateFee=" + lateFee + "]";
	}
	
	
	
	
}
