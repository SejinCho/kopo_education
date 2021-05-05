package kr.ac.kopo.library.member;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.ac.kopo.library.FileUtil;
import kr.ac.kopo.library.MailSend;
import kr.ac.kopo.library.book.BookImpl;
import kr.ac.kopo.library.reservation.ReservationDTO;

public class MemberImpl implements Member{
	Random r = new Random();
	int ran = 0;
	FileUtil memFile = new FileUtil("memberInfoList");
	FileUtil reservationFile = new FileUtil("reservationInfoList");
	
	//=========================================
	//ȸ������
	//=========================================
	
	//��� ���� ���� ���� Ȯ��
	@Override
	public boolean fileExists() {
		boolean result = false;
		result = memFile.fileExists();
		
		return result ;
	}
	
	
	//�̸��� ���� Ȯ��
	@Override
	public boolean isValidEmail(String email) {
		boolean bool = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if(m.matches()) {
			bool = true;
		}
		return bool;
	}
	
	
	//�������� �ʴ� �̸������� Ȯ��
	@Override
	public boolean emailCheck(String newEmail) {
		boolean result = true;
		List<MemberDTO> memInfoList = getMemInfoList();
		
		if(memInfoList == null) return result;
		
		for (MemberDTO memInfo : memInfoList) {
			if(memInfo.getEmail().equals(newEmail)) {
				result = false;
			}
		}
		
		return result;
	}
	
	//�̸��� ������ȣ ����
	@Override
	public void mailSend(String email) {
		ran = r.nextInt(10000);
		MailSend.mailSend(email, ran);
	}
	
	
	//����ڰ� �Է��� ��ȣ�� �̸��Ϸ� ���۵� ������ȣ ��ġ ����
	@Override
	public boolean emailAuth(int inputNum) {
		boolean result = false;
		if(ran == inputNum) result = true;
		return result;
	}
	
	//���̵� �ߺ� Ȯ��
	@Override
	public boolean idCheck(String id) {
		boolean result = true;
		List<MemberDTO> memInfoList = new ArrayList<>();
		memInfoList = (List<MemberDTO>) memFile.fileReader();
		
		if(memInfoList == null) return result;
		
		for(MemberDTO memInfo : memInfoList) {
			if(memInfo.getId().equals(id)) {
				result = false;
			}
		}
		
		return result;
	}
	
	//��й�ȣ ��Ȯ��
	@Override
	public boolean confirmPW(String pw, String repw) {
		boolean result = false;
		if(pw.equals(repw)) 
			result = true;
		
		return result;
	}
	
	// ȸ������ �� ȸ�� ���� ���Ͽ� ����
	@Override
	public boolean memInfoWrite(String name, String email, String id, String pw, int gender) {
		boolean result = false;
		List<MemberDTO> memInfoList = (List<MemberDTO>) memFile.fileReader();
		MemberDTO memDTO = new MemberDTO();
		
		memDTO.setName(name);
		memDTO.setEmail(email);
		memDTO.setId(id);
		memDTO.setPw(pw);
		memDTO.setGender(gender);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		memDTO.setJoinDate(format.format(time.getTime()));
		
		if( memInfoList == null ) {
			memInfoList = new ArrayList<MemberDTO>();
		}
		memInfoList.add(memDTO);
		
		result = memFile.fileWriter(memInfoList);
		return result ; 
	}
	
	
	//=========================================
	// �α���
	//=========================================
	//�α���
	@Override
	public int login(String id, String pw) {
		int result = 0; //���̵� �������� ����
		
		List<MemberDTO> memInfoList = getMemInfoList();
		if(memInfoList == null) {
			result = 0;
			return result;
		}
		for(MemberDTO memInfo : memInfoList) {
			if(memInfo.getId().equals(id)) { //���̵� ����
				if(memInfo.getPw().equals(pw)) {
					result = 1; //�α��� ����
				}else {
					result = 2; //���̵�� ��й�ȣ�� ��ġ���� ����
				}
				
			}
		}
		return result;
	}
	

	//ȸ�� ���� ��������(�� ��)
	@Override
	public MemberDTO getMemInfo(String id){
		List<MemberDTO> meminfoList = getMemInfoList();
		MemberDTO memInfo = new MemberDTO();
		
		for(MemberDTO info : meminfoList) {
			if (info.getId().equals(id) ) {
				memInfo = info;
			}
		}
		return memInfo;
	}

	//ȸ������ ��ü ��������
	@Override
	public List<MemberDTO> getMemInfoList() {
		List<MemberDTO> memberinfoList = new ArrayList<MemberDTO>();
		memberinfoList = (List<MemberDTO>) memFile.fileReader();
		
		return memberinfoList;
	}
	
	//=========================================
	// ����������
	//=========================================
	
	//��������
	@Override
	public void myInfoPrint(String id) {
		MemberDTO memInfo = getMemInfo(id);
		System.out.println("\n�̸�\t�̸���\t\t\t���̵�\t\t����\tȸ������ ��¥");
		System.out.println("=========================================================\n");
		System.out.print(memInfo.getName() + "\t");
		System.out.print(memInfo.getEmail() + "\t");
		System.out.print(memInfo.getId() + "\t\t");
		if(memInfo.getGender() == 1) {
			System.out.print("��\t");
		}else {
			System.out.print("��\t");
		}
		System.out.print(memInfo.getJoinDate() + "\t");
		System.out.println();
	}
	
	//��й�ȣ ����
	@Override
	public boolean updatePW(String id, String updatePW) {
		boolean result = false;
		List<MemberDTO> memInfoList = getMemInfoList();
		for(MemberDTO memInfo : memInfoList) {
			if(memInfo.getId().equals(id)) {
				memInfo.setPw(updatePW);
				break;
			}
		}
		result = memFile.fileWriter(memInfoList);
		
		return result;
	}
	
}
