package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.service.AccountInfoService;
import kr.ac.kopo.service.ManagerService;
import kr.ac.kopo.service.MemberService;
import kr.ac.kopo.service.TransferInfoService;
import kr.ac.kopo.util.ServiceFactory;
import kr.ac.kopo.util.SessionFactory;

public abstract class BaseUI implements IAccountInfoUI{
	private Scanner sc;
	protected MemberService memberService ;
	protected AccountInfoService accountInfoService;
	protected TransferInfoService transferInfoService;
	protected ManagerService managerService;
	
	public BaseUI() {
		sc = new Scanner(System.in);
		memberService = ServiceFactory.memberService;
		accountInfoService = ServiceFactory.accountInfoService;
		transferInfoService = ServiceFactory.transferInfoService;
		managerService = ServiceFactory.managerService;
	}
	
	@Override
	public void top() throws Exception {
		System.out.println("==========================================");
		System.out.println(SessionFactory.getSessionInstance("name") + "님 어서오세요. 통합계좌 관리시스템입니다.");
		System.out.println("==========================================");
	}
	
	
	public String getString(String msg) {
		System.out.print(msg);
		String str = sc.nextLine();
		return str;
	}
	
	public int getInt(String msg) {
		System.out.print(msg);
		int num = sc.nextInt();
		sc.nextLine();
		return num;
	}
}
