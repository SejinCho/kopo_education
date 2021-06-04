package kr.ac.kopo.util;

import kr.ac.kopo.service.AccountInfoService;
import kr.ac.kopo.service.ManagerService;
import kr.ac.kopo.service.MemberService;
import kr.ac.kopo.service.TransferInfoService;

public class ServiceFactory {
	public final static MemberService memberService = new MemberService();
	public final static AccountInfoService accountInfoService = new AccountInfoService();
	public final static TransferInfoService transferInfoService = new TransferInfoService();
	public final static ManagerService managerService = new ManagerService();

}
