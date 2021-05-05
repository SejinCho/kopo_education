package kr.ac.kopo.library.reservation;

import java.util.List;

public interface Reservation {
	public int bookReturn(int bookIndex, String id, String memEmail); //도서 반납
	public List<ReservationDTO> allUserReservationInfoList(); //모든 유저의 예약 내역
	public void returnInfoPrint(String id) ; //반납 정보 출력
	public void reservationInfoPrint(String id) ; //예약 정보 출력
	public List<ReservationDTO> getReservationInfo(String id); //해당 유저의 예약 정보
	public boolean bookReservation(int bookIndex, String id); //도서 대여
	public int calDate(String reservationDate, String returnDate); //날짜 차이 계산
}
