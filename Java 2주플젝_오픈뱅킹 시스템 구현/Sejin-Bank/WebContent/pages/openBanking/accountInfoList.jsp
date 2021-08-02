<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>계좌 조회</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/account/openBanking.css">
	
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/board/bootstrap.js"></script>
</head>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp"></jsp:include>
	</header>
	<div class="container qna-board-container">
	
		<!-- 종범 은행 -->
		<c:if test="${! empty accountInfo_J}">
				<div class="openBanking-block">
					<h2 class="text-center">JBMorgan 은행 계좌 조회</h2>
					
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
							<c:forEach items="${accountInfo_J}" var="accountInfo" varStatus="status">
								<tr> 
									<td>${accountInfo.account_number}</td>
									<td>${accountInfo.nickname }</td>
									<td>${accountInfo.reg_date }</td>
									<td><fmt:formatNumber value="${accountInfo.balance }" pattern="#,###" /></td>
									<td><a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/openBanking/detail.do?account_number=${accountInfo.account_number}&bank_code=${accountInfo.bank_code}">조회</a>
										<a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/openBanking/transfer.do?account_number=${accountInfo.account_number}&bank_code=${accountInfo.bank_code}">이체</a>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					<hr>
				</div>
		</c:if>
		
		
		<!-- 양현석 은행 -->
		<c:if test="${! empty accountInfo_Y}">
				<div class="openBanking-block">
					<h2 class="text-center">YGBank 은행 계좌 조회</h2>
					
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
							<c:forEach items="${accountInfo_Y}" var="accountInfo" varStatus="status">
								<tr> 
									<td>${accountInfo.account_number}</td>
									<td>${accountInfo.nickname }</td>
									<td>${accountInfo.reg_date }</td>
									<td><fmt:formatNumber value="${accountInfo.balance }" pattern="#,###" /></td>
									<td><a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/openBanking/detail.do?account_number=${accountInfo.account_number}&bank_code=${accountInfo.bank_code}">조회</a>
										<a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/openBanking/transfer.do?account_number=${accountInfo.account_number}&bank_code=${accountInfo.bank_code}">이체</a>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					<hr>
				</div>
		</c:if>
		
		<!-- 소영 은행 -->
		<c:if test="${! empty accountInfo_D}">
				<div class="openBanking-block">
					<h2 class="text-center">DONJO 은행 계좌 조회</h2>
					
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
							<c:forEach items="${accountInfo_D}" var="accountInfo" varStatus="status">
								<tr> 
									<td>${accountInfo.account_number}</td>
									<td>${accountInfo.nickname }</td>
									<td>${accountInfo.reg_date }</td>
									<td><fmt:formatNumber value="${accountInfo.balance }" pattern="#,###" /></td>
									<td><a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/openBanking/detail.do?account_number=${accountInfo.account_number}&bank_code=${accountInfo.bank_code}">조회</a>
										<a class="qna-board-btn btn-default" href="<%=request.getContextPath()%>/openBanking/transfer.do?account_number=${accountInfo.account_number}&bank_code=${accountInfo.bank_code}">이체</a>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					<hr>
				</div>
		</c:if>
		
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
	
</body>
</html>