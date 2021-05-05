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
	
	//두 날짜 차이(반납일 - 대여일)
	@Override
	public int calDate(String reservationDate, String returnDate){
	    
	    int result = 0;
	    try { // String Type을 Date Type으로 캐스팅하면서 생기는 예외로 인해 여기서 예외처리 해주지 않으면 컴파일러에서 에러가 발생해서 컴파일을 할 수 없다.
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        // date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
	        Date FirstDate = format.parse(reservationDate);
	        Date SecondDate = format.parse(returnDate);
	        
	        // Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
	        // 연산결과 -950400000. long type 으로 return 된다.
	        long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	        
	        // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다. 
	        // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
	        long calDateDays = calDate / ( 24*60*60*1000); 
	 
	        result = (int) Math.floor(calDateDays);
	        } catch(Exception e) {
	            // 예외 처리
	        }
	    
	    return result;
	}    
	
	//도서 대여
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
					bookInfo.setReservationCheck(1); //대여 불가 상태로 변경
				}
				//남은 수량 변경
				bookInfo.setBookQuantity(bookQuantity);
		
				
				//도서 예약 파일에 추가
				reservationFileUtil.fileExists(); //파일 존재 여부 확인
				
				reservationInfoList = (List<ReservationDTO>) reservationFileUtil.fileReader();
				if(reservationInfoList == null) {
					reservationInfoList = new ArrayList<ReservationDTO>();
				}
				
				//예약 정보 추가
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
		
		//파일 다시 저장
		result = bookFileUtil.fileWriter(bookInfoList);
		result = reservationFileUtil.fileWriter(reservationInfoList);
		return result;
	}
	
	//특정 회원의 대여한 도서 정보(id 하나)
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
	
	
	//도서 목록 프린트(id가 있는 경우 1명의 도서 목록, all인 경우 전체 도서 목록)
	@Override
	public void reservationInfoPrint(String id) {
		List<ReservationDTO> reservationInfoList = null;
		BookDTO bookDTO = null;
		
		if(id.equalsIgnoreCase("all")) { //전체 회원 대여 목록
			reservationInfoList = allUserReservationInfoList();
		}else { //한명 대여 목록
			reservationInfoList = getReservationInfo(id);
		}
		
		if(reservationInfoList == null) {
			System.out.println();
			System.out.println("       *** 대여한 도서가 존재하지 않습니다. ***       ");
			return;
		}
		
		System.out.println("\n도서번호\t도서명\t\t저자\t\t출판사\t\t분류\t\t대여 날짜\t반납한 날짜\t연체료");
		System.out.println("===================================================================================================");
		for(ReservationDTO reservationInfo : reservationInfoList) {
			System.out.print(reservationInfo.getBookIndex() + "\t\t" );
			
			bookDTO = bookImpl.getBookInfo(reservationInfo.getBookIndex());
			if(bookDTO == null) {
				System.out.println();
				System.out.println("       *** 대여한 도서가 존재하지 않습니다. ***       ");
				return;
			}
			System.out.print(bookDTO.getBookName() + "\t" );
			System.out.print(bookDTO.getAuthor() + "\t\t" );
			System.out.print(bookDTO.getPublisher() + "\t" );
			System.out.print(bookDTO.getCategory() + "\t" );
			System.out.print(reservationInfo.getReservationDate()+ "\t" );
			if(reservationInfo.getReturnDate() != null) {
				System.out.print(reservationInfo.getReturnDate() + "\t");
				System.out.print(reservationInfo.getLateFee()+"원");
				
			}else {
				System.out.print("");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	//반납해야 하는 도서 목록 프린트(id가 있는 경우 1명의 반납 도서 목록, all인 경우 전체 반납 도서 목록)
	@Override
	public void returnInfoPrint(String id) {
		List<ReservationDTO> reservationInfoList = null;
		BookDTO bookDTO = null;
		
		if(id.equalsIgnoreCase("all")) { //전체 회원 대여 목록
			reservationInfoList = allUserReservationInfoList();
		}else { //한명 대여 목록
			reservationInfoList = getReservationInfo(id);
		}
		
		if(reservationInfoList == null) {
			System.out.println();
			System.out.println("       *** 반납해야하는 도서가 존재하지 않습니다. ***       ");
			return;
		}
		
		int cnt = 1;
		System.out.println("\n도서번호\t도서명\t\t저자\t\t출판사\t\t분류\t\t대여 날짜");
		System.out.println("===================================================================================================");
		for(ReservationDTO reservationInfo : reservationInfoList) {
			if(reservationInfo.getReturnDate() != null && reservationInfoList.size()==cnt) {
				System.out.println();
				System.out.println("       *** 반납해야하는 존재하지 않습니다. ***       ");
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
				System.out.print(reservationInfo.getLateFee()+"원");
				
			}else {
				System.out.print("");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	
	//전체 회원의 대여한 도서 목록 가져오기
	@Override
	public List<ReservationDTO> allUserReservationInfoList(){
		List<ReservationDTO> allUserReservationInfoList = (List<ReservationDTO>) reservationFileUtil.fileReader();

		return allUserReservationInfoList;
	}
	
	//========================================================
	// 도서 반납
	//========================================================
	@Override
	public int bookReturn(int bookIndex, String id, String memEmail) {
		int result = 1; //1. 도서 번호를 잘못 입력 2. 시스템 상의 오류로 반납 실패 3. 반납 성공
		int reservationIndex = 0;
		
		//특정 유저의 대여 리스트 가져오기
		List<ReservationDTO> reservationInfoList = getReservationInfo(id);
		//마지막에 전체 회원 reservation 파일 update
		List<ReservationDTO> allUserReservationInfoList = allUserReservationInfoList();
		//bookInfoList 파일 update
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
				
				//1. reservationInfoList에 도서 반납 날짜 찍기
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar time = Calendar.getInstance();
				reservationInfo.setReturnDate(format.format(time.getTime()));
				
				BookDTO savaBookInfo = new BookDTO();
				//2. 책 정보에 도서 수량 변경
				for(BookDTO bookInfo : bookInfoList) {
					if(bookInfo.getBookIndex() == bookIndex) {
						savaBookInfo = bookInfo;
						int bookQuantity = bookInfo.getBookQuantity();
						bookInfo.setBookQuantity(bookQuantity + 1);
						
						//3. 책 정보에서 대여 상태 변경 (만약 원래 대여불가였다면 대여 가능으로)
						if(bookQuantity == 0) {
							bookInfo.setReservationCheck(0);
						}
						break;
					}
				}
				
				//4. 대여 날짜랑 반납 날짜랑 비교해서 연체되었는지 확인
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
			// 도서 정보 저장
			boolean result1 = bookFileUtil.fileWriter(bookInfoList);
			
			// 예약 내역 저장
			boolean result2 = reservationFileUtil.fileWriter(allUserReservationInfoList);
			if(! result1 && ! result2) {
				result = 2;
			}
		}
		return result;
		
	}
	
	
}
