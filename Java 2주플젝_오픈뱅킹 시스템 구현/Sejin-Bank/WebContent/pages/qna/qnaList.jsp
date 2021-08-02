<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Q&A</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/board/bootstrap.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/board/bootstrap.js"></script>
	
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		function selChange() {
			var sel = document.getElementById('cntPerPage').value;
			location.href="<%=request.getContextPath() %>/qna/qnaList.do?nowPage=${paging.nowPage}&cntPerPage=" + sel
		}
	</script>
</head>
<body>
	<!-- header -->
	<header>
		<jsp:include page="/pages/include/topMenu.jsp"></jsp:include>
	</header>
	<div class="container qna-board-container">
		<h2 class="text-center">Q&A 게시판</h2>
		
		<div style="float: right;">
			<select id="cntPerPage" name="sel" onchange="selChange()">
				<option value="5"
					<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄 보기</option>
				<option value="10"
					<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
				<option value="15"
					<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
				<option value="20"
					<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
			</select>
		</div>
		
		<table class="table table-hover">
			<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
			</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${viewAll}" var="board" varStatus="status">
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
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<hr>
		<a class="qna-board-btn btn-default pull-right" href="<%=request.getContextPath()%>/qna/write.do?type=new">글쓰기</a>
		<div class="qna-board-text-center">
			<ul	class="pagination">
				<c:if test="${paging.startPage != 1 }">
					<li><a href="<%=request.getContextPath() %>/qna/qnaList.do?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
				</c:if>
				<c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="p">
					<c:choose>
						<c:when test="${p == paging.nowPage }">
							<li><a>${p}</a></li>
						</c:when>
						<c:when test="${p != paging.nowPage }">
							<li><a href="<%=request.getContextPath() %>/qna/qnaList.do?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a></li>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.endPage != paging.lastPage}">
					<li><a href="<%=request.getContextPath() %>/qna/qnaList.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<footer>
		<jsp:include page="/pages/include/bottom.jsp"></jsp:include>	
	</footer>
</body>
</html>