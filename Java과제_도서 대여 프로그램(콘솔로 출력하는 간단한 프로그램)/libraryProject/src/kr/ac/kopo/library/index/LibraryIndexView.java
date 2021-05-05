package kr.ac.kopo.library.index;


import java.util.Scanner;

import kr.ac.kopo.library.SHA256;
import kr.ac.kopo.library.book.BookImpl;
import kr.ac.kopo.library.member.MemberDTO;
import kr.ac.kopo.library.member.MemberImpl;
import kr.ac.kopo.library.reservation.ReservationImpl;
import kr.ac.kopo.manage.ManageDTO;
import kr.ac.kopo.manage.ManageImpl;

public class LibraryIndexView {
	Scanner sc;
	MemberDTO memDTO ;
	ManageDTO manageDTO;
	
	public LibraryIndexView() {
		sc = new Scanner(System.in);
		memDTO = new MemberDTO();
		manageDTO = new ManageDTO();
	}
	
	//���� �ޱ�
	public int getNum(String str) {
		System.out.print(str);
		int num = sc.nextInt();
		sc.nextLine();
		return num;
	}
	
	//���ڿ� �ޱ�
	public String getString(String str) {
		System.out.print(str);
		String result = sc.nextLine();
		return result;
	}
	
	//============================================
	// index ȭ��
	//============================================
	public void index() {
		MemberImpl memImpl = new MemberImpl();
		SHA256 sha256 = new SHA256();
		//��ü ȸ������ ��������
		
		System.out.println("[[ ���� �������� ���� ���� ȯ���մϴ�. ]] \n");
		System.out.println("1. ȸ�� �α���");
		System.out.println("2. ȸ�� ����");
		System.out.println("3. ������ �α���");
		int num = getNum("\n�޴��� �����ϼ��� : ");
		
		
		String id = "";
		String pw = "";
		
		switch (num) {
		case 1: //�α���
			System.out.println("\n[[ �α��� ]] \n");
			int loginCheck = 0;
			do {
				id = getString("\n���̵� �Է��ϼ��� : ");
				pw = sha256.encryptSHA256(getString("��й�ȣ�� �Է��ϼ��� : "));
				loginCheck = memImpl.login(id, pw);
				
				switch (loginCheck) {
				case 1: //�α��� ����
					System.out.println("\n�α��� �Ǿ����ϴ�.");
					MemberDTO dto = memImpl.getMemInfo(id);
					memDTO.setName(dto.getName());
					memDTO.setEmail(dto.getEmail());
					memDTO.setId(dto.getId());
					memDTO.setPw(dto.getPw());
					memDTO.setGender(dto.getGender());
					memDTO.setJoinDate(dto.getJoinDate());
					
					main();
					break;
					
				case 2: 
					System.out.println("\n���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					break;
	
				default:
					System.out.println("\n���̵� �������� �ʽ��ϴ�.");
					break;
				}
			} while(loginCheck != 1);
			
			break;
			
		case 2: //ȸ�� ����
			System.out.println("\n[[ ȸ������ ]] \n");
			
			//���� ���翩�� Ȯ��
			memImpl.fileExists(); //���� ���ٸ� ����
			
			String name = getString("�̸��� �Է��ϼ��� : "); 
			
			boolean isValidEmail = false; //�̸��� ���� Ȯ��
			boolean emailCheckBool = false; //�̸��� �ߺ� Ȯ��
			boolean idCheckBool = false; //���̵� �ߺ� Ȯ��
			
			do {
				String email = getString("\n�̸����� �Է��ϼ��� : "); 
				
				//*****�̸���***** 
				//��ȿ�� �˻�
				isValidEmail = memImpl.isValidEmail(email); //���� Ȯ��
				emailCheckBool = memImpl.emailCheck(email); //�ߺ� �ƴ��� Ȯ��
				
				if(isValidEmail && emailCheckBool) { //�̸��� ��ȿ�� �˻� ���
					memImpl.mailSend(email); //�̸��� ����
					int inputNum = getNum("\n�̸��Ϸ� ������ȣ�� ���۵Ǿ����ϴ�....\n������ȣ�� �Է��ϼ��� : ");
					boolean emailAuth = memImpl.emailAuth(inputNum); // �̸��� ������ȣ ��ġ ����
					
					if(! emailAuth) {
						do {
							System.out.println("\n!!!!!�̸��� ������ȣ�� ��ġ���� �ʽ��ϴ�!!!!!");
							inputNum = getNum("������ȣ�� �ٽ� �Է��ϼ��� : ");
							emailAuth = memImpl.emailAuth(inputNum);
						} while(! emailAuth);
					}
					
					System.out.println("\n**�̸��� ������ ���������� �Ϸ�Ǿ����ϴ�!!**");
					//*****���̵�*****
					id = getString("\n���̵� �Է��ϼ��� : "); 
					idCheckBool = memImpl.idCheck(id);
					if( !idCheckBool ) {
						do {
							id = getString("\n�̹� �����ϴ� ���̵��Դϴ�. �ٽ� �Է����ּ���. : ");
							idCheckBool = memImpl.idCheck(id);
						}while(!idCheckBool) ;
					}
					
					//*****��й�ȣ*****
					
					boolean confirmPW = false;
					pw = sha256.encryptSHA256(getString("��й�ȣ�� �Է��ϼ��� : "));
						do {
						String repw = sha256.encryptSHA256(getString("������ ��й�ȣ�� �� �� �� �Է����ּ��� : "));
						confirmPW = memImpl.confirmPW(pw, repw);
						if(confirmPW) {
							System.out.println("\n��й�ȣ�� ��ġ�մϴ�.");
						}else {
							System.out.println("\n��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						}
					}while(! confirmPW);
					int gender = getNum("������ �Է��ϼ���. (����: 1, ����: 2) : ") ;
					
					memDTO.setName(name);
					memDTO.setEmail(email);
					memDTO.setId(id);
					
					boolean result = memImpl.memInfoWrite(name, email, id, pw, gender); //ȸ������ ���Ͽ� ����
					
					if(result) {
						System.out.println("\n **ȸ������ �Ϸ�!!**");
						//ȸ������ ���� �� main �������� �̵�
						main();
					}
					
				
				}else if(! isValidEmail){
					System.out.println("\n�߸��� �̸��� �����Դϴ�. �ٽ� �Է����ּ���. ");
				}else if(! emailCheckBool) {
					System.out.println("\n�̹� �����ϴ� �̸����Դϴ�. �ٽ� �Է����ּ���. ");
				}
			} while (!isValidEmail || ! emailCheckBool);
				
			break;
			
		case 3:  //������ ȭ��
			//������ �α���
			ManageImpl manageImpl = new ManageImpl();
			int managerLoginCheck = 0;
			do {
				String managerId = getString("\n���̵� �Է��ϼ��� : ");
				String managerPw = getString("��й�ȣ�� �Է��ϼ��� : ");
				managerLoginCheck = manageImpl.managerLoginCheck(managerId, managerPw);
				
				switch (managerLoginCheck) {
				case 1: //�α��� ����
					System.out.println("\n�α��� �Ǿ����ϴ�.");
					manage();
					break;
					
				case 2: 
					System.out.println("\n���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					break;
	
				default:
					System.out.println("\n���̵� �������� �ʽ��ϴ�.");
					break;
				}
			} while(managerLoginCheck != 1);
			
			break;
			
		default :
			System.out.println("\n�޴��� ����� ��������...\n");
			index();
		}
	}
	
	//============================================
	// ȸ�� ���� ȭ��
	//============================================
	public void main() {
		BookImpl bookImpl = new BookImpl();
		MemberImpl memImpl = new MemberImpl();
		ReservationImpl reservationImpl = new ReservationImpl();
		SHA256 sha256 = new SHA256();
		
		System.out.println("\n[[ �ȳ��ϼ���. " + memDTO.getName() + "�� �ݰ����ϴ�. ]]");
		System.out.println("\n1. ����Ʈ���� ����");
		System.out.println("2. ���� ��ü ����");
		System.out.println("3. ���� �˻��ϱ�");
		System.out.println("4. ���� �ݳ��ϱ�");
		System.out.println("5. ����������");
		System.out.println("6. �α׾ƿ�");
		int num = getNum("\n�޴��� �����ϼ��� : ");
		
		switch (num) {
		case 1: //����Ʈ ���� ����
			bookImpl.printBestSeller();
			System.out.println();
			main();
			break;
			
		case 2: //���� ��ü ����
			System.out.println("==============================================================");
			System.out.println("                           ��ü ���� ����Ʈ");
			System.out.println("==============================================================");
			bookImpl.bookInfoPrint(0);
			
			reservation();
			
			main();
			break;
			
		case 3: //���� �˻�
			System.out.println("==============================================================");
			System.out.println("                           ���� �˻�");
			System.out.println("==============================================================");
			String keyword = getString("�������� �Է����ּ���. : ") ;
			bookImpl.selectBookListPrint(keyword);
			
			reservation();
			
			main();
			break;
			
		case 4: //���� �ݳ�
			reservationImpl.returnInfoPrint(memDTO.getId());
			int returnResult = 0;
			do {
				int returnBookId = getNum("\n�ݳ��� ���� ��ȣ�� �Է����ּ���(���� ȭ������ ���ư��÷��� -1�� �Է����ּ���) : ");
				if(returnBookId == -1) { 
					main();
					break;
				}
				
				returnResult = reservationImpl.bookReturn(returnBookId, memDTO.getId(), memDTO.getEmail());
				if(returnResult == 3) {
					System.out.println("�ݳ��� �Ϸ�Ǿ����ϴ�.");
				}else if(returnResult == 2){
					System.out.println("�ý��� ���� ������ �ݳ��� �����Ͽ����ϴ�. �ٽ� �� �� �õ����ּ���.");
				}else if(returnResult ==1){
					System.out.println("���� ��ȣ�� �߸��Է��ϼ̽��ϴ�.");
					
				}
			} while(returnResult != 3);
			main();
			break;
			
		case 5: //����������
			System.out.println("\n1. ��������");
			System.out.println("2. ��й�ȣ ����");
			System.out.println("3. �뿩 ���� ���");
			int mypageMenuSelect = getNum("\n�޴��� �����ϼ��� : ");
			
			switch (mypageMenuSelect) {
			case 1: //��������
				
				memImpl.myInfoPrint(memDTO.getId());
				main();
				break;
				
			case 2: //��� ����
				int idPwCheck = 0;
				
				do {
					String pw = sha256.encryptSHA256(getString("\n��й�ȣ�� �Է��ϼ��� : "));
					idPwCheck = memImpl.login(memDTO.getId(), pw);
					
					if(idPwCheck == 2) {
						System.out.println("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					}
					
				} while(idPwCheck != 1);
				
				//��й�ȣ ��Ȯ��
				boolean confirmPW = false;
				String updatePW = sha256.encryptSHA256(getString("\n������ ��й�ȣ�� �Է��ϼ��� : "));
				
					do {
						
					String repw = sha256.encryptSHA256(getString("\n������ ��й�ȣ�� �� �� �� �Է����ּ��� : "));
					confirmPW = memImpl.confirmPW(updatePW, repw);
					
					if(confirmPW) {
						boolean updatePwCheck = memImpl.updatePW(memDTO.getId(), updatePW);

						if(updatePwCheck) {
							System.out.println("\n!!!!!!!��й�ȣ ���� �Ϸ�!!!!!!!");
						}
						else {
							System.out.println("\n��й�ȣ ���濡 �����Ͽ����ϴ�.");
							main();
						}
						
					}else {
						System.out.println("\n��й�ȣ�� ��ġ���� �ʽ��ϴ�. T-T");
					}
				}while(! confirmPW);
				
				main();
				break;
			case 3: //�뿩 ���� ���
				reservationImpl.reservationInfoPrint(memDTO.getId());
				main();
				break;
			
			
			default:
				System.out.println("�߸� �����ϼ̽��ϴ�. ���� ȭ������ �̵��ϰڽ��ϴ�.");
				main();
				break;
			}
			
			break;
			
		case 6: //�α׾ƿ�
			String answer = getString("�α׾ƿ� �Ͻðڽ��ϱ�???(Y or N �Է�) ");
			if(answer.equalsIgnoreCase("Y")) {
				memDTO.setName(null);
				memDTO.setEmail(null);
				memDTO.setId(null);
				memDTO.setPw(null);
				memDTO.setGender(-1);
				memDTO.setJoinDate(null);
				index();
			}else {
				main();
			}

			break;
			
		default:
			System.out.println("\n����� �Է����ּ���!!!");
			main();
			break;
		}
		
	}
	
	//============================================
	// ���� ���� ȭ�� 
	//============================================
	public void reservation() {
		boolean reservationCheck = false;
		do {
			String answer = getString("\n������ �뿩�Ͻðڽ��ϱ�????( Y / N ) : ");
			
			if(answer.equals("Y") || answer.equals("y")) {
				reservationCheck = true;
				
			}else if(answer.equals("N") || answer.equals("n")) {
				main();
				
			}else {
				System.out.println("Y �Ǵ� N�� �Է����ּ��� ! ");
			}
		} while(! reservationCheck);
		ReservationImpl reservationImple = new ReservationImpl();
		int bookIndex = getNum("�뿩�� ���� ��ȣ�� �Է����ּ��� : ");
		boolean reservationResult = reservationImple.bookReservation(bookIndex, memDTO.getId());
		
		if(reservationResult) {
			System.out.println("\n!!!�뿩�� �Ϸ�Ǿ����ϴ�. �������������� Ȯ�����ּ���.!!!");
		}
		
	}
	
	//============================================
	//������ ȭ��
	//============================================
	public void manage() {
		ManageImpl manageImpl = new ManageImpl();
		BookImpl bookImpl = new BookImpl();
		bookImpl.bookFileExists();
		
		System.out.println("\n[[ �ȳ��ϼ���. ������ ȭ���Դϴ�. ]]");
		System.out.println("\n1. ��ü ���� ���� ����");
		System.out.println("2. ���� �߰�");
		System.out.println("3. ���� ����");
		System.out.println("4. ���� ���� ����");
		System.out.println("5. ���� �˻�");
		System.out.println("6. ȸ�� ���� ����");
		System.out.println("7. �α׾ƿ�");
		int num = getNum("\n�޴��� �����ϼ��� : ");
		
		switch (num) {
		case 1: 
			System.out.println("==============================================================");
			System.out.println("                           ��ü ���� ����Ʈ");
			System.out.println("==============================================================");
			bookImpl.bookInfoPrint(0);
			System.out.println();
			int anw = getNum("���ϴ� �޴��� �Է����ּ���. (1. ���� �����ϱ� 2. ���� �����ϱ� 3. ���� ȭ������ �̵�) : ");
			do {
				if(anw == 1) {
					boolDelete();
				}else if(anw == 2) {
					bookModify();
				}else if(anw == 3) {
					manage();				
				}else {
					System.out.println("����� �Է����ּ���..!");
				}
			}while(anw < 1 || anw > 3);
			
			break;
			
		case 2: 
			System.out.println("==============================================================");
			System.out.println("                           ���� ���� �߰�");
			System.out.println("==============================================================");
			
			String bookName = getString("���� �̸��� �Է��ϼ���. : ");
			String author = getString("������ ���ڸ� �Է��ϼ���. : ");
			String publisher = getString("������ ���ǻ縦 �Է��ϼ���. : ");
			String category = getString("������ ī�װ��� �Է��ϼ���. : ");
			int bookQuantity = getNum("������ ������ �Է��ϼ���. : ");
			
			boolean bookAddResult = manageImpl.bookInfoAdd(bookName, author, publisher, category, bookQuantity);
			
			if(bookAddResult) {
				System.out.println("\n���� �߰� �Ϸ�");
			}
			manage();
			break;
		case 3:
			System.out.println("==============================================================");
			System.out.println("                           ���� ����");
			System.out.println("==============================================================");
			bookImpl.bookInfoPrint(0);
			boolDelete();
			break;
		case 4:
			System.out.println("==============================================================");
			System.out.println("                           ���� ����");
			System.out.println("==============================================================");
			bookImpl.bookInfoPrint(0);
			bookModify();
			break;
		case 5: //���� �˻�
			System.out.println("==============================================================");
			System.out.println("                           ���� �˻�");
			System.out.println("==============================================================\n\n");
			String keyword = getString("�������� �Է����ּ���. : ") ;
			bookImpl.selectBookListPrint(keyword);
			manage();
			break;
		case 6: //ȸ�� ����
			System.out.println("==============================================================");
			System.out.println("                          ��ü ȸ�� ����");
			System.out.println("==============================================================\n");
			manageImpl.memInfoListPrint();
			manage();
			break;
		case 7: //�α׾ƿ�
			String answer = getString("�α׾ƿ� �Ͻðڽ��ϱ�???(Y or N �Է�) ");
			if(answer.equalsIgnoreCase("Y"))
				index();
			manage();
		default:
			System.out.println("\n����� �Է����ּ���!!!");
			manage();
			break;
		}
		
	}
	
	//���� ����
	public void boolDelete() {
		ManageImpl manageimpl = new ManageImpl();
		
		int deleteIndex = getNum("\n������ ������ ��ȣ�� �Է����ּ��� : ");
		boolean result = manageimpl.bookInfoDelete(deleteIndex);
		if(result) {
			System.out.println("*****���������� ������ �����Ǿ����ϴ�*****");
		}else {
			System.out.println("*****���� ��ȣ�� �߸� �Է��ϼ̰ų� ���� �� ������ �߻��߽��ϴ�****");
		}
		manage();
	}
	
	//���� ����
	public void bookModify() {
		ManageImpl manageimpl = new ManageImpl();
		int deleteIndex = getNum("\n������ ������ ��ȣ�� �Է����ּ��� : ");
		int modifyInfo = getNum("������ ������ �������ּ���.(1. ������ 2. ���� 3. ���ǻ� 4. �з� 5. ���� ����) : ");
		boolean result = manageimpl.bookInfoModify(deleteIndex, modifyInfo);
		if(result) {
			System.out.println("*****���������� ������ �����Ǿ����ϴ�*****");
		}else {
			System.out.println("*****���� ��ȣ�� �߸� �Է��ϼ̰ų� ���� �� ������ �߻��߽��ϴ�****");
		}
		
		manage();
	}
	
}//class
