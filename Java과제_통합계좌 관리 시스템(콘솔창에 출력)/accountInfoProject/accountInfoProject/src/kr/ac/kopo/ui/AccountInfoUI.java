package kr.ac.kopo.ui;

import kr.ac.kopo.util.SessionFactory;

public class AccountInfoUI extends BaseUI{
	
	public void process() throws Exception{
		index();
		while(true) {
			top();
			contents();
		}
	}
	
	@Override
	public void contents() throws Exception{
		
			IAccountInfoUI ui = null;
			int type = menu();
			switch (type) {
			case 1: //계좌등록
				ui = new AccountRegistrationUI();
				break;
				
			case 2: //계좌 별칭 수정
				ui = new AccountModifyNicknameUI();
				break;
				
			case 3: //계좌 삭제
				ui = new AccountDeleteUI();
				break;
			
			case 4: //전체 계좌 조회
				ui = new AccountAllSelectUI();
				break;
			
			case 5: //계좌 검색
				ui = new AccountSelectUI();
				break;
				
			case 6: //은행별 검색
				ui = new BankSelectUI();
				break;
				
			case 7 : //입금
				ui = new DepositUI();
				break;
				
			case 8 : //출금
				ui = new WithdrawUI();
				break;
				
			case 9: //계좌이체
				ui = new TransferUI();
				break;
				
			case 10 : //계좌 생성 
				ui = new AccountCreateUI();
				break;
			case 11 :
				ui = new TransferInfoUI();
				break;
			case 12: //로그아웃
				SessionFactory.setSessionInstance("exitMem");
				process();
				break;
			default :
				System.out.println("1 - 11중에 입력하세요.");
				break;
			}
			if(ui != null) {
				ui.contents();
			}
		
		
		
	}
	
	public void index() throws Exception{
		int indexType = indexMenu();
		boolean indexBool = false;
		do {
			IAccountInfoUI ui = null;
			switch (indexType) {
			case 1: //로그인
				ui = new LoginUI();
				indexBool = true;
				break;
				
			case 2: //회원가입
				ui = new JoinUI();
				indexBool = true;
				break;
				
			case 3: //관리자 로그인
				ui = new ManagerLoginUI();
				break;
			case 4: //종료
				ui = new ExitUI();
				break;
			}
			
			if(ui != null) {
				ui.contents();
			}
			
		}while(! indexBool);
	}
	
	public int indexMenu() {
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 관리자 로그인");
		System.out.println("4. 종료");
		int num = getInt("원하는 메뉴를 입력하세요 : ");
		return num;
	}
	
	
	public int menu() {
		System.out.println("1. 계좌 등록");
		System.out.println("2. 계좌 별칭 수정");
		System.out.println("3. 계좌 삭제");
		System.out.println("4. 전체 계좌 조회");
		System.out.println("5. 계좌 검색");
		System.out.println("6. 은행별 검색(하나, 농협, 기업, 산업, 국민)");
		System.out.println("7. 입금");
		System.out.println("8. 출금");
		System.out.println("9. 계좌 이체");
		System.out.println("10. 계좌 생성");
		System.out.println("11. 전체 거래 내역");
		System.out.println("12. 로그아웃");
		int type = getInt("원하는 메뉴를 선택하세요. : ");
		return type;
	}
	
	public void managerContents() throws Exception{
		IAccountInfoUI ui = null;
		while(true) {
			int type = managerMenu();
			switch (type) {
			case 1: //회원 관리
				ui = new ManageMemberUI();
				break;
			case 2: //회원 통계 정보
				ui = new MemberStatisticsUI();
				break;
			case 3: 
				ui = new ManageAccountInfo();
				break;
			case 4: //로그아웃
				SessionFactory.setSessionInstance("exit");
				process();
				break;
			default :
				System.out.println("1 - 4 중에 입력하세요.");
				continue;
			}
			if(ui != null) {
				ui.contents();
			}
		}
		
	}
	
	public int managerMenu() {
		System.out.println("==========================================");
		System.out.println(SessionFactory.getSessionInstance("manager_id") + "님 어서오세요. 통합계좌 관리시스템입니다.");
		System.out.println("==========================================");
		System.out.println("1. 회원 정보");
		System.out.println("2. 회원 통계 정보");
		System.out.println("3. 전체 계좌 조회");
		System.out.println("4. 로그아웃");
		int type = getInt("원하는 메뉴를 선택하세요. : ");
		return type;
	}
}
