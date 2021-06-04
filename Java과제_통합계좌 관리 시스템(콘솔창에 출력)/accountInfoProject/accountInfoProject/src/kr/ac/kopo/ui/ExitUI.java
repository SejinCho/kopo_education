package kr.ac.kopo.ui;

public class ExitUI extends BaseUI{
	@Override
	public void contents() throws Exception {
		System.out.println("=============================");
		System.out.println("통합계좌 관리시스템을 종료합니다.");
		System.out.println("=============================");
		System.exit(0);
	}
}
