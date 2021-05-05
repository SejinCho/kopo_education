package kr.ac.kopo.library;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import kr.ac.kopo.library.book.BookDTO;
import kr.ac.kopo.library.reservation.ReservationDTO;

public class MailLatefee {
	public static void mailSend(String email, int latefee, BookDTO bookInfo, ReservationDTO reservationInfo) {
		String mail_id = "�̸��� �Է�" ;
		String mail_pw = "��й�ȣ �Է�";
		
		//SMTP ���� ���� ����
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465); 
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail_id, mail_pw);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail_id));

            //������ ���� �ּ�
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 

            // Subject
            message.setSubject("[���� ������] �¶��� ���� �뿩 ��ü�� �ȳ� �����Դϴ�."); //���� ������ �Է�

            // Text
            message.setText("�뿩 �ϼ̴� ���� [ " + bookInfo.getBookName() + " ]" + "�� ���� ��ü��� "  + latefee + "�� �Դϴ�."
            		+ "\n ���� �� : [" + bookInfo.getBookName() + "] , ���� :" + bookInfo.getAuthor()
            		+ "\n �뿩 ��¥ : " + reservationInfo.getReservationDate()
            		+ "\n �ݳ� ��¥ : " + reservationInfo.getReturnDate());    //���� ������ �Է�

            // send the message
            Transport.send(message); ////����
            System.out.println("��ü�ᰡ �߻��Ͽ� �̿� ���õ� �̸����� �����߽��ϴ�. Ȯ�����ּ���!!!");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
        
        
	}
}
