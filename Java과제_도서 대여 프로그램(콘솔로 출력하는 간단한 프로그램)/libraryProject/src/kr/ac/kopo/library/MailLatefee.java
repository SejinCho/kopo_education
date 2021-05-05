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
		String mail_id = "이메일 입력" ;
		String mail_pw = "비밀번호 입력";
		
		//SMTP 서버 정보 설정
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

            //수신자 메일 주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 

            // Subject
            message.setSubject("[세진 도서관] 온라인 도서 대여 연체료 안내 메일입니다."); //메일 제목을 입력

            // Text
            message.setText("대여 하셨던 도서 [ " + bookInfo.getBookName() + " ]" + "의 관한 연체료는 "  + latefee + "원 입니다."
            		+ "\n 도서 명 : [" + bookInfo.getBookName() + "] , 저자 :" + bookInfo.getAuthor()
            		+ "\n 대여 날짜 : " + reservationInfo.getReservationDate()
            		+ "\n 반납 날짜 : " + reservationInfo.getReturnDate());    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            System.out.println("연체료가 발생하여 이와 관련된 이메일을 전송했습니다. 확인해주세요!!!");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
        
        
	}
}
