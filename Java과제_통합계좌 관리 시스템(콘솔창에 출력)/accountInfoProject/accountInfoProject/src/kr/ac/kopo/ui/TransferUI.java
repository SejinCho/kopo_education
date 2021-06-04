package kr.ac.kopo.ui;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import kr.ac.kopo.vo.AccountInfoVO;

public class TransferUI extends BaseUI{	//������ü
	@Override
	public void contents() throws Exception {
		
		DecimalFormat format = new DecimalFormat("###,###");
		
		//�۱��� ���� ���� Ȯ��(�����ִ� ���ุ)
		//�۱ݹ��� ������ ���� ���̺� �ִ��� Ȯ��
		//�����ִٸ� �ܾ� update, ���ٸ� �۱��� ���ุ update
		
		//map�� �����ؼ� dao�� ������
		Map<String, Object> map = new HashMap<String, Object>();
		AccountInfoVO sendVo = null;
		AccountInfoVO recieveVo = null;
		
		
		String send_bank = "";
		String send_account_number = "";
		
		
		//=======================================================
		//	�۱��� ���� ����
		//=======================================================
		
		boolean send_accountInfoBool = false;
		do {
			
			//��ü ���� ��ȸ
			IAccountInfoUI ui = new AccountAllSelectUI();
			ui.contents();
			System.out.println();
			
			send_bank = getString("�۱��� ������ �Է��ϼ���.(�ϳ�, ����, ���, ���, ����) : ");
			send_account_number = getString("�۱��� ���¸� �Է��ϼ���.('-'�� �Է����� ������.) : ");
			
			//���� ���� ��������
			sendVo = accountInfoService.oneAccountInfo(send_bank, send_account_number, "me");
			
			if(sendVo == null) {
				System.out.println("���¹�ȣ�� �߸� �Է��߽��ϴ�.");
				continue;
			}else {
				String send_account_passwd = getString("������ ��й�ȣ�� �Է��ϼ��� : ");
				if(! send_account_passwd.equals(sendVo.getAccount_passwd())) {
					System.out.println("���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					continue;
				}
				
				System.out.println("===========================================");
				System.out.println(send_bank +"������ " + send_account_number + " ���� ����");
				System.out.println("===========================================");
				System.out.print("��Ī : " + sendVo.getNickname());
				System.out.print(", ���¹�ȣ : " + sendVo.getAccount_number());
				System.out.print(", �ܾ� : " + format.format(sendVo.getBalance()));
				System.out.print(", ��� ��¥ : " + sendVo.getRegistration_date());
				System.out.println();
				
				
				send_accountInfoBool = true;
			}
		}while(! send_accountInfoBool);
		
		map.put("send_bank", send_bank);
		map.put("sendVo", sendVo);
		
		//=======================================================
		//	�۱ݹ��� ���� ����
		//=======================================================
		
		String recieve_bank = getString("�۱ݹ��� ������ �����ϼ��� : ");
		String recieve_account_number = getString("�۱ݹ��� ���¸� �Է��ϼ���('-'�� �Է����� ������.) : ");
		
		//�۱ݹ��� ���� ���� ��������
		recieveVo = accountInfoService.oneAccountInfo(recieve_bank, recieve_account_number,"other");
		
		
		//�� �۱��Ұ���
		int money = 0;
		boolean moneyBool = false;
		do {
			money = getInt("�󸶸� �۱��Ͻðڽ��ϱ�? : ");
			if(money > sendVo.getBalance()) {
				System.out.println("�ܾ׺��� ���� �۱��� �� �����ϴ�. ���� �ܾ��� " + format.format(sendVo.getBalance()) + "�� �Դϴ�.");
				continue;
			}
			moneyBool = true;
		}while(! moneyBool);
		map.put("money", money);
		
		if(recieveVo == null) { //�۱ݹ޴� ���� ������ ���� ���̺� ���� ���
			map.put("recieve_bank", "null");
			map.put("outside_account_bank", recieve_bank);
			map.put("outside_account_number", recieve_account_number);
			
		}else { //�۱ݹ޴� ���� ������ ���� ���̺� �ִ� ���
			map.put("recieve_bank", recieve_bank);
			map.put("recieveVo", recieveVo);
		}
		
		boolean result = transferInfoService.transfer(map);
		if(result) {
			System.out.println("������ü �Ϸ�");
		}else {
			System.out.println("������ü ����");
		}
		
		
		
	}

}
