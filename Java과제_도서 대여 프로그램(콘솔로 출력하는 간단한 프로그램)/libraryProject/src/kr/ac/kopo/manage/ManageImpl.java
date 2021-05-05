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
	
	//������ �α���
	@Override
	public int managerLoginCheck(String id, String pw) {
		int result = 0;
		if(id.equals("admin")) {
			if(pw.equals("admin")) {
				result = 1;
			}else {
				result = 2;
			}
			
		}else { //���̵� �������� ����
			result = 3;
		}
		return result;
	}
	
	
	//ȸ������ ���
	@Override
	public void memInfoListPrint() {
		List<MemberDTO> meminfoList = new ArrayList<MemberDTO>();
		
		meminfoList = memImpl.getMemInfoList();
		
		
		System.out.println("��ȣ\t�̸�\t�̸���\t\t\t���̵�\t\t����\tȸ������ ��¥");
		if(meminfoList == null) {
			System.out.println();
			System.out.println("       *** ȸ�� ������ �������� �ʽ��ϴ�. ***       ");
			return;
		}
		
		int no = 1;
		for(MemberDTO memInfo : meminfoList) {
			System.out.print(no++ + "\t");
			System.out.print(memInfo.getName() + "\t");
			System.out.print(memInfo.getEmail() + "\t");
			System.out.print(memInfo.getId() + "\t\t");
			if(memInfo.getGender() == 1) {
				System.out.print("��\t");
			}else {
				System.out.print("��\t");
			}
			System.out.print(memInfo.getJoinDate() + "\t");
		}
		System.out.println();
	}
	
	//���� ���� �߰�
	@Override
	public boolean bookInfoAdd(String bookName, String author, String publisher, String category, int bookQuantity) {
		boolean result = false;
		List<BookDTO> bookInfoList = null;
		BookDTO bookDTO = new BookDTO();
		
		//��ü ���� ����Ʈ �����ͼ� ���ο� ���� ���� ����Ʈ�� add
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
		
		//���� ����Ʈ ���� update
		result = bookFile.fileWriter(bookInfoList);
		
		return result ;
	}
	
	//å ����
	@Override
	public boolean bookInfoDelete(int bookIndex) {
		boolean result = false;
		
		//��ü ���� ����Ʈ
		List<BookDTO> bookInfoList = bookImpl.getBookInfoList();
		
		for(BookDTO bookInfo : bookInfoList) {
			if(bookInfo.getBookIndex() == bookIndex) {
				bookInfoList.remove(bookInfo);
				result = bookFile.fileWriter(bookInfoList);
				break;
			}
		}
		
		//���� ����Ʈ ���� update
		
		
		return result ;
	}
	
	
	//���� ����
	@Override
	public boolean bookInfoModify(int bookIndex, int modifyInfo) {
		bookImpl.bookInfoPrint(bookIndex);
		LibraryIndexView view = new LibraryIndexView();
		
		boolean result = false;
		//��ü ���� ����Ʈ
		List<BookDTO> bookInfoList = bookImpl.getBookInfoList();
		
		//���� ����
		for(BookDTO bookInfo : bookInfoList) {
			if(bookInfo.getBookIndex() == bookIndex) {
				String modifyStr = "";
				int modifyNum = 0;
				
				if(modifyInfo > 0 && modifyInfo < 5) {
					modifyStr = view.getString("������ ������ �Է��ϼ��� : ");
				}else if(modifyInfo == 5) {
					modifyNum = view.getNum("������ ���� ������ �Է��ϼ��� : ");
				}
					
				switch (modifyInfo) {
				case 1: //������
					bookInfo.setBookName(modifyStr);
					break;
				case 2: //����
					bookInfo.setAuthor(modifyStr);
					break;
				case 3: //���ǻ�
					bookInfo.setPublisher(modifyStr);
					break;
				case 4: //�з�
					bookInfo.setCategory(modifyStr);
					break;
				case 5: //���� ����
					bookInfo.setBookQuantity(modifyNum);
					break;
				default:
					break;
				}
				
				//���� ����Ʈ ���� update
				result = bookFile.fileWriter(bookInfoList);
				break;
			}
		}

		return result;
	}
	
}
