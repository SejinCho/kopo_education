package kr.ac.kopo.util;

import kr.ac.kopo.vo.ManagerVO;
import kr.ac.kopo.vo.MemberVO;

public class SessionFactory {
	
	private static MemberVO member = new MemberVO();
	private static ManagerVO manager = new ManagerVO();
	
	public static void setSessionInstance(MemberVO member) {
		SessionFactory.member.setMember_index(member.getMember_index());
		SessionFactory.member.setId(member.getId());
		SessionFactory.member.setName(member.getName());
		
	}
	
	public static void setSessionInstance(ManagerVO getManager) {
		SessionFactory.manager.setManager_id(getManager.getManager_id());
		SessionFactory.manager.setManager_index(getManager.getManager_index());
	}
	
	public static void setSessionInstance(String exit) {
		if(exit.equals("exitMem")) {
			SessionFactory.member.setMember_index(null);
			SessionFactory.member.setId(null);
			SessionFactory.member.setName(null);
		}else {
			SessionFactory.manager.setManager_id(null);
			SessionFactory.manager.setManager_index(null);
		}
		
	}
	
	public static String getSessionInstance(String str) {
		switch (str) {
		case "id":
			return SessionFactory.member.getId();
		case "member_index" :
			return SessionFactory.member.getMember_index();
		case "name":
			return SessionFactory.member.getName();
		case "manager_id":
			return SessionFactory.manager.getManager_id();
		case "manager_index" :
			return SessionFactory.manager.getManager_index();
		}
		return null;
	}
	
	
}
