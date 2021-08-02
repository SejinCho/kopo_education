<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>계좌 조회</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/bootstrap.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/board/bootstrap.js"></script>
	
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
				<c:forEach items="${accountInfoList}" var="accountInfo" varStatus="status">
					<tr> 
						<td>${accountInfo.account_number }</td>
						<td>${accountInfo.nickname }</td>
						<td>${accountInfo.reg_date }</td>
						<td><fmt:formatNumber value="${accountInfo.balance }" pattern="#,###" /></td>
						<td><a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/account/detail.do?account_number=${accountInfo.account_number}">조회</a>
							<a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/account/transfer.do?account_number=${accountInfo.account_number}">이체</a>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<hr>
		
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
</body>
</html>