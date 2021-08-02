<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>계좌 조회</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/bootstrap.css">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/board/bootstrap.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	
</head>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp"></jsp:include>
	</header>
	<div class="container qna-board-container">
		<h2 class="text-center">세진은행 계좌 조회</h2>
		
		<table class="table table-hover">
			<thead>
			<tr>
				<th>계좌번호</th>
				<th>별칭</th>
				<th>신규일</th>
				<th>잔액</th>
				<th>업무</th>
			</tr>
			</thead>
			<tbody>
				<!-- 
				<c:forEach items="${accountInfoList}" var="accountInfo" varStatus="status">
					<tr> 
						<td>${accountInfo.account_number}</td>
						<td>${accountInfo.nickname }</td>
						<td>${accountInfo.reg_date }</td>
						<td>${accountInfo.balance }</td>
						<td><a class="qna-board-btn btn-default"  href="#" onclick="modifyForm()">별칭수정</a>
						</td>
					</tr>
				</c:forEach>
				 -->
				 <tr> 
					<td>1234</td>
					<td>1234</td>
					<td>1234</td>
					<td>1234</td>
					<td>
						<button type="button" id="showModal">수정</button>
						<a class="qna-board-btn btn-default"  href="#" onclick="modifyForm()">별칭수정</a>
					</td>
				</tr>
			</tbody>
		</table>
		<hr>
		
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <!-- Login -->
	        <form>
	          <div class="mb-3">
	            <label for="exampleInputEmail1" class="form-label">Email address</label>
	            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
	            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
	          </div>
	          <div class="mb-3">
	            <label for="exampleInputPassword1" class="form-label">Password</label>
	            <input type="password" class="form-control" id="exampleInputPassword1">
	          </div>
	          <div class="mb-3 form-check">
	            <input type="checkbox" class="form-check-input" id="exampleCheck1">
	            <label class="form-check-label" for="exampleCheck1">Check me out</label>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Submit</button>
	      </div>
	    </div>
	  </div>
	</div>

	
</body>
</html>