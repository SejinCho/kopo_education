package kr.ac.kopo.auth.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.util.RandomNO;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsAuthController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//핸드폰 번호
		String phone = request.getParameter("phone");
		String ran = RandomNO.getRanNo(6, 1);
		
		System.out.println("핸드폰 ran : " + ran);
		
		
		
		String api_key = "NCSYPTNBUIL12T6I";
	    String api_secret = "MJBLDTP2MEQBH6UTOO4C8F5D1PIEO94Q";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", phone); //받는 사람
	    params.put("from", "01025235526"); //보내는 사람
	    params.put("type", "SMS");
	    params.put("text", "[세진은행] 인증번호 : " + ran);
	    params.put("app_version", "test app 1.2"); // application name and version
	    
	    
	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      request.setAttribute("ran", ran);
	      System.out.println(obj.toString());
	      
	    } catch (CoolsmsException e) {
	      System.out.println("에러인가");
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
		
	    
	    
	    
		//request.setAttribute("ran", ran);
		return "/pages/auth/smsAuth.jsp";
	}
}
