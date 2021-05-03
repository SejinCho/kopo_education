---
typora-copy-images-to: images
---



## WEB/WAS/DBMS 연동

- #### **emp table을 linux_scott 계정으로 가져오기(app deploy를 위한 프로젝트에서 emp 테이블을 사용하기 위함**

  - emp 오른쪽 버튼 > 익스포트

  ![image-20210430171815902](images/image-20210430171815902.png)

  ![image-20210430171939051](images/image-20210430171939051.png)

  ![image-20210430171958888](images/image-20210430171958888.png)

  ![image-20210430172033181](images/image-20210430172033181.png)

  ![image-20210430172655750](images/image-20210430172655750.png)

  

  - **계정에 접속하기 위해서 오라클DB는 인스턴스와 리스너를 따로 시작하도록 되어 있다.** 

    - 인스턴스 시작/중지

    ```shell
    sqlplus '/as sysdba'
    startup
    shutdown
    ```

    ![image-20210430171214441](images/image-20210430171214441.png)

    

    - 리스너 시작/중지

    ```shell
    lsnrctl start
    lsnrctl stop
    ```

    ![image-20210430171241028](images/image-20210430171241028.png)

  

  

  ![image-20210430172446031](images/image-20210430172446031.png)

  ![image-20210430172536804](images/image-20210430172536804.png)

  ![image-20210430172724140](images/image-20210430172724140.png)

  ![image-20210430172835769](images/image-20210430172835769.png)

  ![image-20210430173520567](images/image-20210430173520567.png)

  ![image-20210430173545112](images/image-20210430173545112.png)

  ![image-20210430173605692](images/image-20210430173605692.png)

  ![image-20210430173622893](images/image-20210430173622893.png)

  ![image-20210430173704549](images/image-20210430173704549.png)

  

- ### **tomcat에 올릴 war 생성을 위한 이클립스 설치**

  https://www.eclipse.org/downloads/packages/

  ![image-20210430174216041](images/image-20210430174216041.png)

  ![image-20210430174654842](images/image-20210430174654842.png)

  

- **이클립스에 새로운 프로젝트 생성**

  ![image-20210430174721321](images/image-20210430174721321.png)

  ![image-20210430175022516](images/image-20210430175022516.png)

  ![image-20210430175458458](images/image-20210430175458458.png)

  ![image-20210430175526242](images/image-20210430175526242.png)

  ![image-20210430175601696](images/image-20210430175601696.png)

  ![image-20210430175710132](images/image-20210430175710132.png)

  - dbconn 생성 
    - 에러 발생

  ![image-20210430175949966](images/image-20210430175949966.png)

  - 위의 에러 해결 방법

  ![image-20210430180023444](images/image-20210430180023444.png)

  ![image-20210430180106163](images/image-20210430180106163.png)

  ![image-20210430180136413](images/image-20210430180136413.png)

  ![image-20210430180158473](images/image-20210430180158473.png)

  - apache tomcat v9.0 설치

    http://tomcat.apache.org/

  ![image-20210430180408377](images/image-20210430180408377.png)

  

  ![image-20210430180811355](images/image-20210430180811355.png)

  - 압축 풀어서 program files에 넣기

  ![image-20210430180855485](images/image-20210430180855485.png)

  ![image-20210430181013947](images/image-20210430181013947.png)

  

  ![image-20210430180946779](images/image-20210430180946779.png)

  ![image-20210430181057629](images/image-20210430181057629.png)

  ![image-20210430181121610](images/image-20210430181121610.png)

  - new - class

  ![image-20210430181136982](images/image-20210430181136982.png)

  ![image-20210430181759522](images/image-20210430181759522.png)

  ![image-20210430181942148](images/image-20210430181942148.png)

  - emp table의 column 넣기

  ![image-20210430181916841](images/image-20210430181916841.png)

  - web.xml 설정
    - display-name에 프로젝트 명을 작성
    - 위의 dbconn calss에 서블릿 매핑을 dbconn으로 설정되어 있어서 servlet-name을 dbconn으로 설정

  ![image-20210430193624552](images/image-20210430193624552.png)

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
    <display-name>linuxtest</display-name>
    
    <servlet>
    	<servlet-name>dbconn</servlet-name>
    	<servlet-class>kr.ac.kopo.dbconn</servlet-class>
    </servlet>
    
    
    <welcome-file-list>
      <welcome-file>index.html</welcome-file>
      <welcome-file>index.htm</welcome-file>
      <welcome-file>index.jsp</welcome-file>
      <welcome-file>default.html</welcome-file>
      <welcome-file>default.htm</welcome-file>
      <welcome-file>default.jsp</welcome-file>
    </welcome-file-list>
  </web-app>
  ```

  - dbconn class에 db와 연결하는 코드 작성

  ![image-20210430182452029](images/image-20210430182452029.png)

  ```java
  package kr.ac.kopo;
  
  import java.io.IOException;
  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.util.ArrayList;
  import java.util.List;
  
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  
  
  /**
   * Servlet implementation class dbconn
   */
  //db
  @WebServlet("/dbconn")
  public class dbconn extends HttpServlet {
  	private static final long serialVersionUID = 1L;
  	
  	private Connection con = null;
  	private PreparedStatement psmt = null;
  	private ResultSet rs = null;
  	
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		//response.getWriter().append("Served at: ").append(request.getContextPath());
  		
  		List<EMP> empList = new ArrayList<EMP>();
  		
  		try {
  			Class.forName("oracle.jdbc.driver.OracleDriver");
  			String url = "jdbc:oracle:thin:@//192.168.119.111:1521/oracle19";
  			String id = "linux_scott";
  			String pw = "1234";
  			try {
  				con = DriverManager.getConnection(url, id, pw);
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}
  		} catch (ClassNotFoundException e) {
  			e.printStackTrace();
  
  		}
  		
  		try {
  			String sql = "select * from emp";
  
  			psmt = con.prepareStatement(sql);
  			rs = psmt.executeQuery();
  
  			String empno = "";
  			String ename= "";
  			String job= "";
  			String mgr= "";
  			String sal= "";
  			String deptno= "";
  			
  			
  			while (rs.next()) {
  				
  				EMP emp = new EMP();
  				
  				empno = Integer.toString(rs.getInt("EMPNO"));
  				ename = rs.getString("ENAME");
  				job = rs.getString("JOB");
  				mgr = Integer.toString(rs.getInt("MGR"));
  				sal = Integer.toString(rs.getInt("SAL"));
  				deptno = Integer.toString(rs.getInt("DEPTNO"));
  					
  				emp.setDeptno(deptno);
  				emp.setEname(ename);
  				emp.setJob(job);
  				emp.setMgr(mgr);
  				emp.setSal(sal);
  				emp.setDeptno(deptno);
  				
  				empList.add(emp);
  			
  			}
  			
  
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		
  		request.setAttribute("data", empList);
          /*뿌리 경로가 webapp이므로 webapp을 기준으로 경로를 작성을 한다.*/
  		request.getRequestDispatcher("/empList.jsp").forward(request, response);
  
  	}
  
  	/**
  	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  	 */
  //	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //		// TODO Auto-generated method stub
  //		doGet(request, response);
  //	}
  
  }
  ```

  위에서 Dispatcher가 서블릿에서 특정 URL이나 페이지로 이동하는 경로를 /empList.jsp로 설정했으므로 webapp폴더에 JSP File을 생성해야 한다.

  ![image-20210430193208943](images/image-20210430193208943.png)

  ![image-20210430194226680](images/image-20210430194226680.png)

  ```jsp
  <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>empTable</title>
  
  </head>
  <body>
  	<div></div>
  			<c:forEach var="data" items="${data}">
  			<table>
  				<tr>
  					<td>${data.deptno}</td>
  					<td>${data.ename}</a></td>
  					<td>${data.job}</a></td>
  					<td>${data.mgr}</td>
  					<td>${data.sal}</td>
  					<td>${data.deptno}</td>
  				</tr>
  			</table>
  			</c:forEach>
  			
  </body>
  </html>
  ```

  - 라이브러리 넣기

    - JSTL : taglib의 사용을 위해 추가
    - https://mvnrepository.com/artifact/javax.servlet/jstl/1.2

    ![image-20210430183227225](images/image-20210430183227225.png)

    - odbc8.jar 다운
    - https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html

    ![image-20210430183438901](images/image-20210430183438901.png)

    ![image-20210430183531022](images/image-20210430183531022.png)

  - 다운 받은 것 lib 폴더에 넣기

  ![image-20210430184846836](images/image-20210430184846836.png)

  - **build path 설정**

  ![image-20210430184927664](images/image-20210430184927664.png)

  ![image-20210430185031023](images/image-20210430185031023.png)

  ![image-20210430185105080](images/image-20210430185105080.png)

  ![image-20210430185120068](images/image-20210430185120068.png)

  ![image-20210430185152157](images/image-20210430185152157.png)

  ![image-20210430185235202](images/image-20210430185235202.png)

  ![image-20210430185251533](images/image-20210430185251533.png)

  ![image-20210430185317318](images/image-20210430185317318.png)

  ![image-20210430185330915](images/image-20210430185330915.png)

  ![image-20210430185342823](images/image-20210430185342823.png)

  ![image-20210430185401268](images/image-20210430185401268.png)

  ![image-20210430185458078](images/image-20210430185458078.png)

  ![image-20210430185516102](images/image-20210430185516102.png)

  ![image-20210430185542494](images/image-20210430185542494.png)

  - 실행

  ![image-20210430194718516](images/image-20210430194718516.png)

  - 만약 다음과 같은 에러가 발생한다면 8080이 이미 사용 중이므로 port 번호를 변경해줘야 한다.

  ![image-20210430194617923](images/image-20210430194617923.png)

  ![image-20210430194636580](images/image-20210430194636580.png)

  ![image-20210430193249728](images/image-20210430193249728.png)
  
  - war 파일 만들기
  
  ![image-20210503115431951](images/image-20210503115431951.png)
  
  - export
  
  ![image-20210503115550041](images/image-20210503115550041.png)



- #### **window에 있는 war 파일 리눅스로 옮기기(Apache-Tomcat이 깔려있는 리눅스로)**

  - 폴더 확인

  ![image-20210503122202124](images/image-20210503122202124.png)



- #### **window에 있는 war 파일 리눅스로 옮기기(Apache-Tomcat이 깔려있는 리눅스로)**

  - 다운로드 폴더 확인

  ![image-20210503122202124](images/image-20210503122202124.png)

  

- #### 톰캣 구동시 띄워지게 되는 웹서비스 인스턴스의 베이스 디렉토로 war 파일 옮기기

  ![image-20210503122528239](images/image-20210503122528239.png)

  ![image-20210503122347500](images/image-20210503122347500.png)

  

- #### 리눅스에서 test

  - localhost/linuxtest/dbconn

  ![image-20210503141837220](images/image-20210503141837220.png)



- #### **윈도우에서 리눅스의 ip주소를 넣어서 test**

  ![image-20210503172736425](images/image-20210503172736425.png)



- #### cf. 만약에 공유 폴더가 뜨지 않는 경우!!!

  - 공유 폴더가 unmount된 상태이다. 그래서 다시 마운트 시켜줘야 한다.

  ```shell
  sudo mount -t fuse.vmhgfs-fuse .host:/ /mnt/hgfs -o allow_other
  ```

  