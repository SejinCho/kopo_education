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
			case 1: //���µ��
				ui = new AccountRegistrationUI();
				break;
				
			case 2: //���� ��Ī ����
				ui = new AccountModifyNicknameUI();
				break;
				
			case 3: //���� ����
				ui = new AccountDeleteUI();
				break;
			
			case 4: //��ü ���� ��ȸ
				ui = new AccountAllSelectUI();
				break;
			
			case 5: //���� �˻�
				ui = new AccountSelectUI();
				break;
				
			case 6: //���ະ �˻�
				ui = new BankSelectUI();
				break;
				
			case 7 : //�Ա�
				ui = new DepositUI();
				break;
				
			case 8 : //���
				ui = new WithdrawUI();
				break;
				
			case 9: //������ü
				ui = new TransferUI();
				break;
				
			case 10 : //���� ���� 
				ui = new AccountCreateUI();
				break;
			case 11 :
				ui = new TransferInfoUI();
				break;
			case 12: //�α׾ƿ�
				SessionFactory.setSessionInstance("exitMem");
				process();
				break;
			default :
				System.out.println("1 - 11�߿� �Է��ϼ���.");
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
			case 1: //�α���
				ui = new LoginUI();
				indexBool = true;
				break;
				
			case 2: //ȸ������
				ui = new JoinUI();
				indexBool = true;
				break;
				
			case 3: //������ �α���
				ui = new ManagerLoginUI();
				break;
			case 4: //����
				ui = new ExitUI();
				break;
			}
			
			if(ui != null) {
				ui.contents();
			}
			
		}while(! indexBool);
	}
	
	public int indexMenu() {
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("3. ������ �α���");
		System.out.println("4. ����");
		int num = getInt("���ϴ� �޴��� �Է��ϼ��� : ");
		return num;
	}
	
	
	public int menu() {
		System.out.println("1. ���� ���");
		System.out.println("2. ���� ��Ī ����");
		System.out.println("3. ���� ����");
		System.out.println("4. ��ü ���� ��ȸ");
		System.out.println("5. ���� �˻�");
		System.out.println("6. ���ະ �˻�(�ϳ�, ����, ���, ���, ����)");
		System.out.println("7. �Ա�");
		System.out.println("8. ���");
		System.out.println("9. ���� ��ü");
		System.out.println("10. ���� ����");
		System.out.println("11. ��ü �ŷ� ����");
		System.out.println("12. �α׾ƿ�");
		int type = getInt("���ϴ� �޴��� �����ϼ���. : ");
		return type;
	}
	
	public void managerContents() throws Exception{
		IAccountInfoUI ui = null;
		while(true) {
			int type = managerMenu();
			switch (type) {
			case 1: //ȸ�� ����
				ui = new ManageMemberUI();
				break;
			case 2: //ȸ�� ��� ����
				ui = new MemberStatisticsUI();
				break;
			case 3: 
				ui = new ManageAccountInfo();
				break;
			case 4: //�α׾ƿ�
				SessionFactory.setSessionInstance("exit");
				process();
				break;
			default :
				System.out.println("1 - 4 �߿� �Է��ϼ���.");
				continue;
			}
			if(ui != null) {
				ui.contents();
			}
		}
		
	}
	
	public int managerMenu() {
		System.out.println("==========================================");
		System.out.println(SessionFactory.getSessionInstance("manager_id") + "�� �������. ���հ��� �����ý����Դϴ�.");
		System.out.println("==========================================");
		System.out.println("1. ȸ�� ����");
		System.out.println("2. ȸ�� ��� ����");
		System.out.println("3. ��ü ���� ��ȸ");
		System.out.println("4. �α׾ƿ�");
		int type = getInt("���ϴ� �޴��� �����ϼ���. : ");
		return type;
	}
}
