package kr.ac.kopo.auth.controller;

import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.util.RandomNO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailAuthController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email = request.getParameter("email"); //수신자 이메일
		System.out.println("email 확인 : " + email);
		String mail_id = "whtpwls7777@gmail.com" ;
		String mail_pw = "alvhwkddls124!";
		
		String ran = RandomNO.getRanNo(6, 1);
		
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
            message.setSubject("[세진은행] 인증번호 발송 안내 메일입니다."); //메일 제목을 입력

            // Text
            message.setText("인증번호 6자리 : " + ran);    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            
            request.setAttribute("ran", ran);
            
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
		
		return "/pages/auth/emailAuth.jsp";
	}
}
