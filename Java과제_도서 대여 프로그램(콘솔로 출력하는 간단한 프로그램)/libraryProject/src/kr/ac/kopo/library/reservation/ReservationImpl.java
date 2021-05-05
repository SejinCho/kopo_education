package kr.ac.kopo.library.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kr.ac.kopo.library.FileUtil;
import kr.ac.kopo.library.MailLatefee;
import kr.ac.kopo.library.book.BookDTO;
import kr.ac.kopo.library.book.BookImpl;



public class ReservationImpl implements Reservation{
	FileUtil bookFileUtil = new FileUtil("bookInfoList");
	FileUtil reservationFileUtil = new FileUtil("reservationInfoList");
	BookImpl bookImpl = new BookImpl();
	
	//�� ��¥ ����(�ݳ��� - �뿩��)
	@Override
	public int calDate(String reservationDate, String returnDate){
	    
	    int result = 0;
	    try { // String Type�� Date Type���� ĳ�����ϸ鼭 ����� ���ܷ� ���� ���⼭ ����ó�� ������ ������ �����Ϸ����� ������ �߻��ؼ� �������� �� �� ����.
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        // date1, date2 �� ��¥�� parse()�� ���� Date������ ��ȯ.
	        Date FirstDate = format.parse(reservationDate);
	        Date SecondDate = format.parse(returnDate);
	        
	        // Date�� ��ȯ�� �� ��¥�� ����� �� �� ���ϰ����� long type ������ �ʱ�ȭ �ϰ� �ִ�.
	        // ������ -950400000. long type ���� return �ȴ�.
	        long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	        
	        // Date.getTime() �� �ش糯¥�� ��������1970�� 00:00:00 ���� �� �ʰ� �귶������ ��ȯ���ش�. 
	        // ���� 24*60*60*1000(�� �ð����� ���� ������) �� �����ָ� �ϼ��� ���´�.
	        long calDateDays = calDate / ( 24*60*60*1000); 
	 
	        result = (int) Math.floor(calDateDays);
	        } catch(Exception e) {
	            // ���� ó��
	        }
	    
	    return result;
	}    
	
	//���� �뿩
	@Override
	public boolean bookReservation(int bookIndex, String id) {
		boolean result = false;
		List<BookDTO> bookInfoList = (List<BookDTO>) bookFileUtil.fileReader();
		ReservationDTO reservationDTO = new ReservationDTO();
		List<ReservationDTO> reservationInfoList = null;
		
		for(BookDTO bookInfo : bookInfoList) {
			if(bookInfo.getBookIndex() == bookIndex) {
				
				int bookQuantity = bookInfo.getBookQuantity() - 1;
				if(bookQuantity == 0) {
					bookInfo.setReservationCheck(1); //�뿩 �Ұ� ���·� ����
				}
				//���� ���� ����
				bookInfo.setBookQuantity(bookQuantity);
		
				
				//���� ���� ���Ͽ� �߰�
				reservationFileUtil.fileExists(); //���� ���� ���� Ȯ��
				
				reservationInfoList = (List<ReservationDTO>) reservationFileUtil.fileReader();
				if(reservationInfoList == null) {
					reservationInfoList = new ArrayList<ReservationDTO>();
				}
				
				//���� ���� �߰�
				reservationDTO.setReservationIndex(reservationInfoList.size() + 1);
				reservationDTO.setId(id);
				reservationDTO.setBookIndex(bookIndex);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar time = Calendar.getInstance();
				reservationDTO.setReservationDate(format.format(time.getTime()));
				
				reservationInfoList.add(reservationDTO);
				
				break;
			}
		}
		
		//���� �ٽ� ����
		result = bookFileUtil.fileWriter(bookInfoList);
		result = reservationFileUtil.fileWriter(reservationInfoList);
		return result;
	}
	
	//Ư�� ȸ���� �뿩�� ���� ����(id �ϳ�)
	@Override
	public List<ReservationDTO> getReservationInfo(String id) {
		reservationFileUtil.fileExists();
		List<ReservationDTO> allUserReservationInfoList = (List<ReservationDTO>) reservationFileUtil.fileReader();
		List<ReservationDTO> reservationInfoList = new ArrayList<ReservationDTO>();
		
		if(allUserReservationInfoList == null) {
			return allUserReservationInfoList;
		}
		
		for(ReservationDTO reservationInfo: allUserReservationInfoList) {
			if(reservationInfo.getId().equals(id)) {
				reservationInfoList.add(reservationInfo);
			}
		}
		
		return reservationInfoList;
	}
	
	
	//���� ��� ����Ʈ(id�� �ִ� ��� 1���� ���� ���, all�� ��� ��ü ���� ���)
	@Override
	public void reservationInfoPrint(String id) {
		List<ReservationDTO> reservationInfoList = null;
		BookDTO bookDTO = null;
		
		if(id.equalsIgnoreCase("all")) { //��ü ȸ�� �뿩 ���
			reservationInfoList = allUserReservationInfoList();
		}else { //�Ѹ� �뿩 ���
			reservationInfoList = getReservationInfo(id);
		}
		
		if(reservationInfoList == null) {
			System.out.println();
			System.out.println("       *** �뿩�� ������ �������� �ʽ��ϴ�. ***       ");
			return;
		}
		
		System.out.println("\n������ȣ\t������\t\t����\t\t���ǻ�\t\t�з�\t\t�뿩 ��¥\t�ݳ��� ��¥\t��ü��");
		System.out.println("===================================================================================================");
		for(ReservationDTO reservationInfo : reservationInfoList) {
			System.out.print(reservationInfo.getBookIndex() + "\t\t" );
			
			bookDTO = bookImpl.getBookInfo(reservationInfo.getBookIndex());
			if(bookDTO == null) {
				System.out.println();
				System.out.println("       *** �뿩�� ������ �������� �ʽ��ϴ�. ***       ");
				return;
			}
			System.out.print(bookDTO.getBookName() + "\t" );
			System.out.print(bookDTO.getAuthor() + "\t\t" );
			System.out.print(bookDTO.getPublisher() + "\t" );
			System.out.print(bookDTO.getCategory() + "\t" );
			System.out.print(reservationInfo.getReservationDate()+ "\t" );
			if(reservationInfo.getReturnDate() != null) {
				System.out.print(reservationInfo.getReturnDate() + "\t");
				System.out.print(reservationInfo.getLateFee()+"��");
				
			}else {
				System.out.print("");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	//�ݳ��ؾ� �ϴ� ���� ��� ����Ʈ(id�� �ִ� ��� 1���� �ݳ� ���� ���, all�� ��� ��ü �ݳ� ���� ���)
	@Override
	public void returnInfoPrint(String id) {
		List<ReservationDTO> reservationInfoList = null;
		BookDTO bookDTO = null;
		
		if(id.equalsIgnoreCase("all")) { //��ü ȸ�� �뿩 ���
			reservationInfoList = allUserReservationInfoList();
		}else { //�Ѹ� �뿩 ���
			reservationInfoList = getReservationInfo(id);
		}
		
		if(reservationInfoList == null) {
			System.out.println();
			System.out.println("       *** �ݳ��ؾ��ϴ� ������ �������� �ʽ��ϴ�. ***       ");
			return;
		}
		
		int cnt = 1;
		System.out.println("\n������ȣ\t������\t\t����\t\t���ǻ�\t\t�з�\t\t�뿩 ��¥");
		System.out.println("===================================================================================================");
		for(ReservationDTO reservationInfo : reservationInfoList) {
			if(reservationInfo.getReturnDate() != null && reservationInfoList.size()==cnt) {
				System.out.println();
				System.out.println("       *** �ݳ��ؾ��ϴ� �������� �ʽ��ϴ�. ***       ");
				return;
			}else if(reservationInfo.getReturnDate() != null) {
				++cnt;
				continue;
			}
			
			System.out.print(reservationInfo.getBookIndex() + "\t\t" );
			
			bookDTO = bookImpl.getBookInfo(reservationInfo.getBookIndex());
			
			System.out.print(bookDTO.getBookName() + "\t" );
			System.out.print(bookDTO.getAuthor() + "\t\t" );
			System.out.print(bookDTO.getPublisher() + "\t" );
			System.out.print(bookDTO.getCategory() + "\t" );
			System.out.print(reservationInfo.getReservationDate()+ "\t" );
			if(reservationInfo.getReturnDate() != null) {
				System.out.print(reservationInfo.getReturnDate() + "\t");
				System.out.print(reservationInfo.getLateFee()+"��");
				
			}else {
				System.out.print("");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	
	//��ü ȸ���� �뿩�� ���� ��� ��������
	@Override
	public List<ReservationDTO> allUserReservationInfoList(){
		List<ReservationDTO> allUserReservationInfoList = (List<ReservationDTO>) reservationFileUtil.fileReader();

		return allUserReservationInfoList;
	}
	
	//========================================================
	// ���� �ݳ�
	//========================================================
	@Override
	public int bookReturn(int bookIndex, String id, String memEmail) {
		int result = 1; //1. ���� ��ȣ�� �߸� �Է� 2. �ý��� ���� ������ �ݳ� ���� 3. �ݳ� ����
		int reservationIndex = 0;
		
		//Ư�� ������ �뿩 ����Ʈ ��������
		List<ReservationDTO> reservationInfoList = getReservationInfo(id);
		//�������� ��ü ȸ�� reservation ���� update
		List<ReservationDTO> allUserReservationInfoList = allUserReservationInfoList();
		//bookInfoList ���� update
		List<BookDTO> bookInfoList = bookImpl.getBookInfoList();
		
		for(ReservationDTO reservationDTO : reservationInfoList) {
			if(reservationDTO.getBookIndex() == bookIndex && reservationDTO.getReturnDate() == null) {
				result = 2;
				reservationIndex = reservationDTO.getReservationIndex();
				break;
			}else if(reservationDTO.getBookIndex() == bookIndex && reservationDTO.getReturnDate() != null){
				return result;
			}
		}
		
		if(result == 1) {
			return result;
		}
		
		for(ReservationDTO reservationInfo : allUserReservationInfoList) {
			if(reservationInfo.getReservationIndex() == reservationIndex) {
				
				//1. reservationInfoList�� ���� �ݳ� ��¥ ���
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar time = Calendar.getInstance();
				reservationInfo.setReturnDate(format.format(time.getTime()));
				
				BookDTO savaBookInfo = new BookDTO();
				//2. å ������ ���� ���� ����
				for(BookDTO bookInfo : bookInfoList) {
					if(bookInfo.getBookIndex() == bookIndex) {
						savaBookInfo = bookInfo;
						int bookQuantity = bookInfo.getBookQuantity();
						bookInfo.setBookQuantity(bookQuantity + 1);
						
						//3. å �������� �뿩 ���� ���� (���� ���� �뿩�Ұ����ٸ� �뿩 ��������)
						if(bookQuantity == 0) {
							bookInfo.setReservationCheck(0);
						}
						break;
					}
				}
				
				//4. �뿩 ��¥�� �ݳ� ��¥�� ���ؼ� ��ü�Ǿ����� Ȯ��
				int calDateDays = calDate(reservationInfo.getReservationDate(), reservationInfo.getReturnDate());
				if(calDateDays < -14) {
					int lateFee = (Math.abs(calDateDays) - 14) * 200; 
					reservationInfo.setLateFee(lateFee);
					MailLatefee mail = new MailLatefee();
					
					mail.mailSend(memEmail, lateFee, savaBookInfo, reservationInfo);
				}else {
					reservationInfo.setLateFee(0);
				}
				
				result = 3;
				break;
			}
			
			
		}
		
		if (result == 3) {
			// ���� ���� ����
			boolean result1 = bookFileUtil.fileWriter(bookInfoList);
			
			// ���� ���� ����
			boolean result2 = reservationFileUtil.fileWriter(allUserReservationInfoList);
			if(! result1 && ! result2) {
				result = 2;
			}
		}
		return result;
		
	}
	
	
}
