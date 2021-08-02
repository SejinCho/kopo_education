<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Q&A</title>
	
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
		<h2 class="text-center">Q&A 게시판</h2>
		<table class="table table-hover">
			<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${boardList}" var="board" varStatus="status">
					<tr> 
						<td>${status.count}</td>
						<c:if test="${board.re_level eq 0}">
							<td><a href="<%= request.getContextPath()%>/qna/qnaDetail.do?id=${board.id}">${board.title }</a></td>
						</c:if>
						<c:if test="${board.re_level ne 0}">
							<td><a href="<%= request.getContextPath()%>/qna/qnaDetail.do?id=${board.id}">&nbsp; → ${board.title }</a></td>
						</c:if>
						<td>${board.name }</td>
						<td>${board.reg_date }</td>
						<td>${board.view_cnt }</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<hr>
		<a class="qna-board-btn btn-default pull-right" href="<%=request.getContextPath()%>/qna/write.do?type=new">글쓰기</a>
		<div class="qna-board-text-center">
			<ul	class="pagination">
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">6</a></li>
				<li><a href="#">7</a></li>
				<li><a href="#">8</a></li>
				<li><a href="#">9</a></li>
				<li><a href="#">10</a></li>
			</ul>
		</div>
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
</body>
</html>