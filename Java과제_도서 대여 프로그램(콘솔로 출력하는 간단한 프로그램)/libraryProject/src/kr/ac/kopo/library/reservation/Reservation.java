package kr.ac.kopo.library.reservation;

import java.util.List;

public interface Reservation {
	public int bookReturn(int bookIndex, String id, String memEmail); //���� �ݳ�
	public List<ReservationDTO> allUserReservationInfoList(); //��� ������ ���� ����
	public void returnInfoPrint(String id) ; //�ݳ� ���� ���
	public void reservationInfoPrint(String id) ; //���� ���� ���
	public List<ReservationDTO> getReservationInfo(String id); //�ش� ������ ���� ����
	public boolean bookReservation(int bookIndex, String id); //���� �뿩
	public int calDate(String reservationDate, String returnDate); //��¥ ���� ���
}
