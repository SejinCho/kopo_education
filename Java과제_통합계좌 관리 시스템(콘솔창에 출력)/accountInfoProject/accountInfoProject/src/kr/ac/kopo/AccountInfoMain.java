package kr.ac.kopo;

import kr.ac.kopo.ui.AccountInfoUI;

public class AccountInfoMain {
	public static void main(String[] args) {
		AccountInfoUI ui = new AccountInfoUI();
		try {
			ui.process();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
