package kr.ac.kopo.library.book;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	
	public void bestseller() {
		String url = "http://www.kyobobook.co.kr/bestSellerNew/bestseller.laf?range=1&orderClick=DAA" ; 
		Document doc = null; // Document���� �������� ��ü �ҽ��� ����
		try {		
		    
			// Connection ����
			Connection conn = Jsoup.connect(url);
			
			// HTML �Ľ�
			doc = conn.get();
			
		} catch(IOException e) {
			e.printStackTrace();
		}

		//��� Ž��
		Elements element = doc.select("#main_contents");
		
		//Iterator�� ����Ͽ� �ϳ��� �� ��������
		Iterator<Element> ie1 = element.select(".title strong").iterator();
		Iterator<Element> ie2 = element.select(".author").iterator();
		int no = 1;
		
		while (ie1.hasNext() && ie2.hasNext()) {
			System.out.println((no++) + " )");
			System.out.println(ie1.next().text());
			System.out.println(ie2.next().text());
			System.out.println();
			System.out.println("============================================================\n");
		}
	}
	
}
