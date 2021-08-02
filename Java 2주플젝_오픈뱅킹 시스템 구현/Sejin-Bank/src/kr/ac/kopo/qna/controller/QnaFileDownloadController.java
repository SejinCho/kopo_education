package kr.ac.kopo.qna.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.controller.Controller;

public class QnaFileDownloadController implements Controller{
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fileName = request.getParameter("name");
		String saveFileName = "C:/Users/HP/Documents/sejin-bank/qnaImages/" + fileName;
		String contentType = "image/jpg";
		
		File file = new File(saveFileName);
		long fileLength = file.length();
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Content-Type", contentType);
	    response.setHeader("Content-Length", "" + fileLength);
	    response.setHeader("Pragma", "no-cache;");
	    response.setHeader("Expires", "-1;");
	    
	    try(
	    	FileInputStream fis = new FileInputStream(saveFileName);
	   		OutputStream out1 = response.getOutputStream();
		) {
	    	int readCount = 0;
	    	byte[] buffer = new byte[1024];
	    	 while((readCount = fis.read(buffer)) != -1){
	             out1.write(buffer,0,readCount);
	    	 }
	    } catch(Exception ex){
	        throw new RuntimeException("file Save Error");
	    }
		return null;
	}
}
