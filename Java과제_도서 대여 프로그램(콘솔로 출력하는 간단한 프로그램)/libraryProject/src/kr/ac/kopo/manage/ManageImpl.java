package kr.ac.kopo.manage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.ac.kopo.library.FileUtil;
import kr.ac.kopo.library.book.BookDTO;
import kr.ac.kopo.library.book.BookImpl;
import kr.ac.kopo.library.index.LibraryIndexView;
import kr.ac.kopo.library.member.MemberDTO;
import kr.ac.kopo.library.member.MemberImpl;

public class ManageImpl implements Manage{
	MemberImpl memImpl = new MemberImpl();
	BookImpl bookImpl = new BookImpl();
	FileUtil bookFile = new FileUtil("bookInfoList");
	
	//관리자 로그인
	@Override
	public int managerLoginCheck(String id, String pw) {
		int result = 0;
		if(id.equals("admin")) {
			if(pw.equals("admin")) {
				result = 1;
			}else {
				result = 2;
			}
			
		}else { //아이디가 존재하지 않음
			result = 3;
		}
		return result;
	}
	
	
	//회원정보 출력
	@Override
	public void memInfoListPrint() {
		List<MemberDTO> meminfoList = new ArrayList<MemberDTO>();
		
		meminfoList = memImpl.getMemInfoList();
		
		
		System.out.println("번호\t이름\t이메일\t\t\t아이디\t\t성별\t회원가입 날짜");
		if(meminfoList == null) {
			System.out.println();
			System.out.println("       *** 회원 정보가 존재하지 않습니다. ***       ");
			return;
		}
		
		int no = 1;
		for(MemberDTO memInfo : meminfoList) {
			System.out.print(no++ + "\t");
			System.out.print(memInfo.getName() + "\t");
			System.out.print(memInfo.getEmail() + "\t");
			System.out.print(memInfo.getId() + "\t\t");
			if(memInfo.getGender() == 1) {
				System.out.print("남\t");
			}else {
				System.out.print("여\t");
			}
			System.out.print(memInfo.getJoinDate() + "\t");
		}
		System.out.println();
	}
	
	//도서 정보 추가
	@Override
	public boolean bookInfoAdd(String bookName, String author, String publisher, String category, int bookQuantity) {
		boolean result = false;
		List<BookDTO> bookInfoList = null;
		BookDTO bookDTO = new BookDTO();
		
		//전체 도서 리스트 가져와서 새로운 도서 정보 리스트에 add
		bookInfoList = bookImpl.getBookInfoList();
		if(bookInfoList == null) {
			bookInfoList = new ArrayList<BookDTO>();
		}
		
		bookDTO.setBookName(bookName);
		bookDTO.setAuthor(author);
		bookDTO.setPublisher(publisher);
		bookDTO.setCategory(category);
		bookDTO.setReservationCheck(0);
		
		
		int lastIndex = bookInfoList.get(bookInfoList.size()-1).getBookIndex();
		bookDTO.setBookIndex(lastIndex + 1);
		
		
		bookDTO.setBookQuantity(bookQuantity);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		bookDTO.setRegisterDate(format.format(time.getTime()));
		
		bookInfoList.add(bookDTO);
		
		//도서 리스트 파일 update
		result = bookFile.fileWriter(bookInfoList);
		
		return result ;
	}
	
	//책 삭제
	@Override
	public boolean bookInfoDelete(int bookIndex) {
		boolean result = false;
		
		//전체 도서 리스트
		List<BookDTO> bookInfoList = bookImpl.getBookInfoList();
		
		for(BookDTO bookInfo : bookInfoList) {
			if(bookInfo.getBookIndex() == bookIndex) {
				bookInfoList.remove(bookInfo);
				result = bookFile.fileWriter(bookInfoList);
				break;
			}
		}
		
		//도서 리스트 파일 update
		
		
		return result ;
	}
	
	
	//도서 수정
	@Override
	public boolean bookInfoModify(int bookIndex, int modifyInfo) {
		bookImpl.bookInfoPrint(bookIndex);
		LibraryIndexView view = new LibraryIndexView();
		
		boolean result = false;
		//전체 도서 리스트
		List<BookDTO> bookInfoList = bookImpl.getBookInfoList();
		
		//도서 수정
		for(BookDTO bookInfo : bookInfoList) {
			if(bookInfo.getBookIndex() == bookIndex) {
				String modifyStr = "";
				int modifyNum = 0;
				
				if(modifyInfo > 0 && modifyInfo < 5) {
					modifyStr = view.getString("수정할 내용을 입력하세요 : ");
				}else if(modifyInfo == 5) {
					modifyNum = view.getNum("수정할 도서 수량을 입력하세요 : ");
				}
					
				switch (modifyInfo) {
				case 1: //도서명
					bookInfo.setBookName(modifyStr);
					break;
				case 2: //저자
					bookInfo.setAuthor(modifyStr);
					break;
				case 3: //출판사
					bookInfo.setPublisher(modifyStr);
					break;
				case 4: //분류
					bookInfo.setCategory(modifyStr);
					break;
				case 5: //도서 수량
					bookInfo.setBookQuantity(modifyNum);
					break;
				default:
					break;
				}
				
				//도서 리스트 파일 update
				result = bookFile.fileWriter(bookInfoList);
				break;
			}
		}

		return result;
	}
	
}
