package kr.ac.kopo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.conditionTran.util.MainCronTrigger;
import kr.ac.kopo.controller.Controller;
import kr.ac.kopo.controller.HandlerMapping;

public class FrontControllerServlet extends HttpServlet{
	
	private HandlerMapping mappings ;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String propLoc = config.getInitParameter("propertyLocation");
		//스케줄러
		new MainCronTrigger().conditionTransfer();
		
		if(propLoc != null) {
			mappings = new HandlerMapping(propLoc); //�ʱ�ȭ
		} else {
			mappings = new HandlerMapping();
		}
		
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		
		String uri = request.getRequestURI();
		uri = uri.substring(context.length());
		
		Controller control = null;
		
		try {
			
			control = mappings.getController(uri);
			String callPage = control.handleRequest(request, response);
			
			if(callPage.startsWith("redirect:")) { //redirect
				
				callPage = callPage.substring("redirect".length() + 1);
				
				response.sendRedirect(request.getContextPath() + callPage);
			} else { //forward
				RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
				dispatcher.forward(request, response); 
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
